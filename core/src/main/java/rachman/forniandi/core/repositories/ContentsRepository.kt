package rachman.forniandi.core.repositories

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents

interface ContentsRepository {

    fun getDataArticles(): Flow<RemoteResponse<List<Contents>>>
    fun getDataBlogs(): Flow<RemoteResponse<List<Contents>>>

    //paging articles + blogs
    fun doGetPagingArticles(): Flow<PagingData<Contents>>

    fun doGetPagingBlogs(): Flow<PagingData<Contents>>

    fun doGetDetailArticles(id: Int): Flow<RemoteResponse<Contents>>

    fun doGetDetailBlogs(id: Int): Flow<RemoteResponse<Contents>>


    //favorite articles + blogs
    fun getAllFavoriteContents(type: ContentType): Flow<List<FavoriteContentsEntity>>

    suspend fun insertToFavorite(favoriteContentsEntity: FavoriteContentsEntity)

    suspend fun deleteFromFavorite(content: FavoriteContentsEntity)

    fun isFavoriteContent(id: Int, type: ContentType): Flow<Boolean>

    suspend fun deleteAllFavorites()
}