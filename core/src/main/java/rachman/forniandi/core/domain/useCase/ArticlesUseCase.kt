package rachman.forniandi.core.domain.useCase

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents

interface ArticlesUseCase {

    fun getArticles(): Flow<RemoteResponse<List<Contents>>>

    fun getDetailArticles(id: Int): Flow<RemoteResponse<Contents>>

}