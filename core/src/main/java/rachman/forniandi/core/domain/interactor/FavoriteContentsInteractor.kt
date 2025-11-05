package rachman.forniandi.core.domain.interactor

import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase
import rachman.forniandi.core.repositories.ContentsRepository
import javax.inject.Inject

class FavoriteContentsInteractor @Inject constructor(private val contentsRepository: ContentsRepository): FavoriteContentUseCase {

    override fun getFavoriteContents(type: ContentType): Flow<List<FavoriteContentsEntity>> {
        return contentsRepository.getAllFavoriteContents(type)
    }

    override suspend fun updateFavoriteContent(
        content: FavoriteContentsEntity,
        isFavorite: Boolean
    ) {
        if (isFavorite){
            contentsRepository.deleteFromFavorite(content)
        }else{
            contentsRepository.insertToFavorite(content)
        }
    }

    override fun isFavoriteContent(
        id: Int,
        type: ContentType
    ): Flow<Boolean> {
        return contentsRepository.isFavoriteContent(id, type)
    }

    override suspend fun deleteAllFavoriteContents() {
        contentsRepository.deleteAllFavorites()
    }

}