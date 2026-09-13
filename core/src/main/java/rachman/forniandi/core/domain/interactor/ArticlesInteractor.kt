package rachman.forniandi.core.domain.interactor

import rachman.forniandi.core.domain.usecase.ArticlesUseCase
import rachman.forniandi.core.repositories.ContentsRepository
import javax.inject.Inject

class ArticlesInteractor @Inject constructor(
    private val contentsRepository: ContentsRepository
): ArticlesUseCase {
    override fun getDataArticles()= contentsRepository.getDataArticles()

    override fun getPagingArticles() = contentsRepository.doGetPagingArticles()

    override fun getDetailArticles(id: Int) = contentsRepository.doGetDetailArticles(id)

}