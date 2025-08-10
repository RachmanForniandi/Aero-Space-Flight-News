package rachman.forniandi.aerospaceflightnews.repositories

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.aerospaceflightnews.data.network.RemoteResponse
import rachman.forniandi.aerospaceflightnews.domain.Contents

interface ContentsRepository {

    fun doGetArticles(): Flow<RemoteResponse<List<Contents>>>

    fun doGetBlogs(): Flow<RemoteResponse<List<Contents>>>

    fun doGetDetailArticles(id: Int): Flow<RemoteResponse<Contents>>

    fun doGetDetailBlogs(id: Int): Flow<RemoteResponse<Contents>>


}