package rachman.forniandi.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity

interface FavoriteContentUseCase {
    fun getFavoriteContent(): Flow<List<FavoriteContentsEntity>>

    suspend fun updateMovie(id: Int, isFavorite: Boolean)

    fun isFavoriteMovie(id: Int): Flow<Boolean>

}