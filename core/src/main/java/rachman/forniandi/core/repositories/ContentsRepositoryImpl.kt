package rachman.forniandi.core.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rachman.forniandi.core.data.local.ContentsLocalDataSource
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.local.room.ContentsDatabase
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.data.remote.response.RemoteSourceData
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.paging.ContentsRemoteMediator
import rachman.forniandi.core.utilRemote. toContentsEntity
import rachman.forniandi.core.utilRemote.toDetailContentsEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentsRepositoryImpl @Inject constructor(
    private val contentDatabase: ContentsDatabase,
    private val remoteSourceData: RemoteSourceData,
    private val localDataSource: ContentsLocalDataSource,
):ContentsRepository {

    override fun getDataArticles()= flow {
        emit(RemoteResponse.Loading())
        try {
            val response = remoteSourceData.getDataArticles()
            val result = response.results.toContentsEntity(ContentType.ARTICLE)
            emit(RemoteResponse.Success(result))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }

    override fun getDataBlogs()= flow {
        emit(RemoteResponse.Loading())
        try {
            val response = remoteSourceData.getDataBlogs()
            val result = response.results.toContentsEntity(ContentType.BLOG)
            emit(RemoteResponse.Success(result))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }

    override fun doGetPagingArticles()=
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


    override fun doGetPagingBlogs()=
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

    override fun getAllFavoriteContents(type: ContentType): Flow<List<FavoriteContentsEntity>> {
        return localDataSource.getAllFavorites(type)
    }

    override suspend fun insertToFavorite(favoriteContentsEntity: FavoriteContentsEntity) {
        localDataSource.insertFavoriteContent(favoriteContentsEntity)
    }

    override suspend fun deleteFromFavorite(content: FavoriteContentsEntity) {
        localDataSource.deleteFavoriteContent(content)
    }

    override fun isFavoriteContent(
        id: Int,
        type: ContentType
    ): Flow<Boolean> {
        return localDataSource.isFavoriteContent(id,type)
    }

    override suspend fun deleteAllFavorites() {
        localDataSource.deleteAllFavoriteContents()
    }

}



