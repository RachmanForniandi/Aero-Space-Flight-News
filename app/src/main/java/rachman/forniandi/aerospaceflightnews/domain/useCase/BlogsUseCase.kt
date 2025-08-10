package rachman.forniandi.aerospaceflightnews.domain.useCase

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.aerospaceflightnews.data.network.RemoteResponse
import rachman.forniandi.aerospaceflightnews.domain.Contents

interface BlogsUseCase {

    fun getBlogs(): Flow<RemoteResponse<List<Contents>>>

    fun getDetailBlogs(id: Int): Flow<RemoteResponse<Contents>>

}