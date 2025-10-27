package rachman.forniandi.core.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import rachman.forniandi.core.data.local.ContentsLocalDataSource
import rachman.forniandi.core.data.local.entity.RemoteKeys
import rachman.forniandi.core.data.local.room.ContentsDatabase
import rachman.forniandi.core.data.remote.response.RemoteSourceData
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.utilRemote.toContentsEntity

@OptIn(ExperimentalPagingApi::class)
class ContentsRemoteMediator(
    private val type: ContentType,
    private val remoteDataSource: RemoteSourceData,
    private val localDataSource: ContentsLocalDataSource,
    private val database: ContentsDatabase
) : RemoteMediator<Int, Contents>() {

    private val contentsDao = database.contentsDao()
    private val remoteKeysDao = database.remoteKeysDao()

    private companion object {
        const val INITIAL_OFFSET = 0
        private const val TAG = "ContentsRemoteMediator"
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Contents>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> {
                    Log.d(TAG, "Load REFRESH → offset=$INITIAL_OFFSET")
                    INITIAL_OFFSET
                }
                LoadType.PREPEND -> {
                    Log.d(TAG, "Load PREPEND → stop (tidak ada data sebelum batch awal)")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextOffset = remoteKeys?.nextKey
                    Log.d(TAG, "Load APPEND → nextOffset=$nextOffset")
                    nextOffset ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val limit = state.config.pageSize
            Log.d(TAG, "Requesting offset=$offset limit=$limit type=$type")

            val response = when (type) {
                ContentType.ARTICLE -> remoteDataSource.getDataPagingArticles(limit, offset)
                ContentType.BLOG -> remoteDataSource.getDataPagingBlogs(limit, offset)
            }

            val contents = response.results.toContentsEntity(type)
            val endOfPaginationReached = contents.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.deleteAllKeys()
                    localDataSource.clearContentsByType(type)
                }

                val keys = contents.mapIndexed { index, content ->
                    RemoteKeys(
                        id = content.id.toString(),
                        prevKey = if (offset == INITIAL_OFFSET) null else offset - limit,
                        nextKey = if (endOfPaginationReached) null else offset + limit
                    )
                }

                remoteKeysDao.insertAllKeys(keys)
                contentsDao.insertContents(contents)
            }
            Log.d(TAG, "Inserted ${contents.size} items at offset=$offset")

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: Exception) {
            Log.e(TAG, "Error loading data", e)
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