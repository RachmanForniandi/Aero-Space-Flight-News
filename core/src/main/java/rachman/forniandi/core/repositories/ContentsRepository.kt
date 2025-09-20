package rachman.forniandi.core.repositories

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents

interface ContentsRepository {

    fun doGetArticles(): Flow<RemoteResponse<List<Contents>>>

    fun doGetBlogs(): Flow<RemoteResponse<List<Contents>>>

    fun doGetDetailArticles(id: Int): Flow<RemoteResponse<Contents>>

    fun doGetDetailBlogs(id: Int): Flow<RemoteResponse<Contents>>


}