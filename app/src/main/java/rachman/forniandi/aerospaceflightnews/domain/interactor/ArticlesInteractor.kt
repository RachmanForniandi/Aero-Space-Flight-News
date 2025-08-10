package rachman.forniandi.aerospaceflightnews.domain.interactor

import rachman.forniandi.aerospaceflightnews.domain.useCase.ArticlesUseCase
import rachman.forniandi.aerospaceflightnews.repositories.ContentsRepository
import javax.inject.Inject

class ArticlesInteractor @Inject constructor(private val contentsRepository: ContentsRepository): ArticlesUseCase {

    override fun getArticles() = contentsRepository.doGetArticles()

    override fun getDetailArticles(id: Int) = contentsRepository.doGetDetailArticles(id)

}