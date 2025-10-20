package rachman.forniandi.core.paging

import android.net.http.HttpException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import rachman.forniandi.core.data.local.ContentsLocalDataSource
import rachman.forniandi.core.data.local.entity.RemoteKeys
import rachman.forniandi.core.data.local.room.ContentsDatabase
import rachman.forniandi.core.data.remote.response.RemoteSourceData
import rachman.forniandi.core.domain.entity.Contents
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ContentsRemoteMediator (
    private val type: String,
    private val remoteDataSource: RemoteSourceData,
    private val localDataSource: ContentsLocalDataSource,
    private val database: ContentsDatabase
) : RemoteMediator<Int, Contents>() {

    private val contentsDao = database.contentsDao()
    private val remoteKeysDao = database.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Contents>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                    if (prevKey == null){
                        MediatorResult.Success(endOfPaginationReached = true)
                    }
                    prevKey
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    if (nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    nextKey
                }
            }

            val response = when (type.lowercase()) {
                "articles" -> remoteDataSource.getDataPagingArticles(page, state.config.pageSize)
                "blogs" -> remoteDataSource.getDataPagingBlogs(page, state.config.pageSize)
                else -> throw IllegalArgumentException("Unknown content type: $type")
            }

            val contents = response.results
            val endOfPaginationReached: Boolean = contents.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.deleteAllKeys()
                    localDataSource.clearContentsByType(type)
                }

                val keys = contents?.map {
                    RemoteKeys(
                        id = it.id.toString(),
                        prevKey = if (page == 1) null else page?.minus(1),
                        nextKey = if (endOfPaginationReached == true) null else page?.plus(1)
                    )
                }
                remoteKeysDao.insertAllKeys(keys)
                contentsDao.insertContents(contents)

            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Contents>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { content -> remoteKeysDao.getRemoteKeysById(content.id.toString()) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Contents>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { content -> remoteKeysDao.getRemoteKeysById(content.id.toString()) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Contents>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKeysById(id.toString())
            }
        }
    }
}