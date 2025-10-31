package rachman.forniandi.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.domain.entity.ContentType

interface FavoriteContentUseCase {

    fun getFavoriteContents(type: ContentType): Flow<List<FavoriteContentsEntity>>

    suspend fun updateFavoriteContent(content: FavoriteContentsEntity, isFavorite: Boolean)

    fun isFavoriteContent(id: Int, type: ContentType): Flow<Boolean>

}