package rachman.forniandi.aerospaceflightnews.domain.interactor

import rachman.forniandi.aerospaceflightnews.domain.useCase.BlogsUseCase
import rachman.forniandi.aerospaceflightnews.repositories.ContentsRepository
import javax.inject.Inject

class BlogsInteractor @Inject constructor(private val contentsRepository: ContentsRepository): BlogsUseCase{
    override fun getBlogs()= contentsRepository.doGetBlogs()

    override fun getDetailBlogs(id: Int)= contentsRepository.doGetDetailBlogs(id)
}