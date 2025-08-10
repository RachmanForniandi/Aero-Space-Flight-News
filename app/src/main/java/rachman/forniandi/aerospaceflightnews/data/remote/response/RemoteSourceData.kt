package rachman.forniandi.aerospaceflightnews.data.remote.response

import rachman.forniandi.aerospaceflightnews.data.network.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSourceData @Inject constructor(
    private val networkService: NetworkService
){
    suspend fun getDataArticles() = networkService.getArticles()

    suspend fun getDataBlogs() = networkService.getBlogs()

    suspend fun getDetailArticles(id: Int) = networkService.getDetailArticles(id)

    suspend fun getDetailBlogs(id: Int) = networkService.getDetailBlogs(id)


}