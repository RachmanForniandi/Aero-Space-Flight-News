package rachman.forniandi.core.repositories

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents
import kotlin.reflect.KFunction2

interface ContentsRepository {

    fun doGetArticles(): Flow<PagingData<Contents>>

    fun doGetBlogs(): Flow<PagingData<Contents>>

    fun doGetDetailArticles(id: Int): Flow<RemoteResponse<Contents>>

    fun doGetDetailBlogs(id: Int): Flow<RemoteResponse<Contents>>

}