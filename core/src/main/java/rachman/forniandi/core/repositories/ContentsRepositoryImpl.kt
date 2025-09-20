package rachman.forniandi.core.repositories

import kotlinx.coroutines.flow.flow
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.data.remote.response.RemoteSourceData
import rachman.forniandi.core.utilRemote.toContentsEntity
import rachman.forniandi.core.utilRemote.toDetailContentsEntity
import javax.inject.Inject

class ContentsRepositoryImpl @Inject constructor(
    private val remoteSourceData: RemoteSourceData
):ContentsRepository {

    override fun doGetArticles()= flow {
        emit(RemoteResponse.Loading())
        try {
            val response = remoteSourceData.getDataArticles()
            val result = response.results.toContentsEntity()
            emit(RemoteResponse.Success(result))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }

    override fun doGetBlogs()= flow {
        emit(RemoteResponse.Loading())
        try {
            val response = remoteSourceData.getDataBlogs()
            val result = response.results.toContentsEntity()
            emit(RemoteResponse.Success(result))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }


    override fun doGetDetailArticles(id: Int)= flow {
        emit(RemoteResponse.Loading())
        try {
            val response = remoteSourceData.getDetailArticles(id)
            val result = response.toDetailContentsEntity()
            emit(RemoteResponse.Success(result))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }

    override fun doGetDetailBlogs(id: Int)= flow {
        emit(RemoteResponse.Loading())
        try {
            val response = remoteSourceData.getDetailBlogs(id)
            val result = response.toDetailContentsEntity()
            emit(RemoteResponse.Success(result))
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }

}