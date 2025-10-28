package rachman.forniandi.core.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rachman.forniandi.core.data.local.ContentsLocalDataSource
import rachman.forniandi.core.data.local.room.ContentsDatabase
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.data.remote.response.RemoteSourceData
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.paging.ContentsRemoteMediator
import rachman.forniandi.core.utilRemote.toDetailContentsEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentsRepositoryImpl @Inject constructor(
    private val contentDatabase: ContentsDatabase,
    private val remoteSourceData: RemoteSourceData,
    private val localDataSource: ContentsLocalDataSource,
):ContentsRepository {

    override fun doGetArticles()=
        @OptIn(ExperimentalPagingApi::class)
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = ContentsRemoteMediator(
                type = ContentType.ARTICLE,
                remoteDataSource = remoteSourceData,
                localDataSource = localDataSource,
                database = contentDatabase,


            ),
            pagingSourceFactory = {
                localDataSource.getAllContents(ContentType.ARTICLE)
            }
        ).flow


    override fun doGetBlogs()=
        @OptIn(ExperimentalPagingApi::class)
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = ContentsRemoteMediator(
                type = ContentType.BLOG,
                remoteDataSource = remoteSourceData,
                localDataSource = localDataSource,
                database = contentDatabase,
                ),
            pagingSourceFactory = {
                localDataSource.getAllContents(ContentType.BLOG)
            }
        ).flow



    override fun doGetDetailArticles(id: Int)= flow {
        emit(RemoteResponse.Loading())
        try {
            val response = remoteSourceData.getDetailArticles(id)
            val result = response.toDetailContentsEntity(ContentType.ARTICLE)
            emit(RemoteResponse.Success(result))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }

    override fun doGetDetailBlogs(id: Int)= flow {
        emit(RemoteResponse.Loading())
        try {
            val response = remoteSourceData.getDetailBlogs(id)
            val result = response.toDetailContentsEntity(ContentType.BLOG)
            emit(RemoteResponse.Success(result))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(errorMessage = e.message.toString()))
        }
    }

    /*override fun getAllFavoriteContents(type: ContentType): Flow<List<Contents>> {
        localDataSource.getAllFavorites(type)
    }

    override suspend fun addToFavorite(
        contents: Contents,
        type: ContentType
    ) {
        localDataSource.insertFavoriteContent(contents,type)
    }

    override suspend fun removeFromFavorite(contentsId: Int) {
        TODO("Not yet implemented")
    }*/


}



