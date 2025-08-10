package rachman.forniandi.aerospaceflightnews.domain.useCase

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.aerospaceflightnews.data.network.RemoteResponse
import rachman.forniandi.aerospaceflightnews.domain.Contents

interface ContentDetailUseCase {

    fun getDetailContents(id: Int): Flow<RemoteResponse<Contents>>
}