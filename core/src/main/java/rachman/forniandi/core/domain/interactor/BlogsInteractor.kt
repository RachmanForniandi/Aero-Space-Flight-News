package rachman.forniandi.core.domain.interactor

import rachman.forniandi.core.domain.usecase.BlogsUseCase
import rachman.forniandi.core.repositories.ContentsRepository
import javax.inject.Inject

class BlogsInteractor @Inject constructor(private val contentsRepository: ContentsRepository):
    BlogsUseCase {
    override fun getBlogs()= contentsRepository.doGetBlogs()

    override fun getDetailBlogs(id: Int)= contentsRepository.doGetDetailBlogs(id)
}