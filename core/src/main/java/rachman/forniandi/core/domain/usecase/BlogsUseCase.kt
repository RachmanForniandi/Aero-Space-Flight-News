package rachman.forniandi.core.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents

interface BlogsUseCase {

    fun getBlogs(): Flow<PagingData<Contents>>

    fun getDetailBlogs(id: Int): Flow<RemoteResponse<Contents>>

}