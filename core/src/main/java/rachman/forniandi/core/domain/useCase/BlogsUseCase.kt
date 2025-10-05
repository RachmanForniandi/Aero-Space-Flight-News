package rachman.forniandi.core.domain.useCase

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents

interface BlogsUseCase {

    fun getBlogs(): Flow<RemoteResponse<List<Contents>>>

    fun getDetailBlogs(id: Int): Flow<RemoteResponse<Contents>>

}