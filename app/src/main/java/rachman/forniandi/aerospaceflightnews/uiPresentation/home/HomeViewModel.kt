package rachman.forniandi.aerospaceflightnews.uiPresentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import rachman.forniandi.core.domain.usecase.ArticlesUseCase
import rachman.forniandi.core.domain.usecase.BlogsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    articlesUseCase: ArticlesUseCase,
    blogsUseCase: BlogsUseCase
) : ViewModel() {

    val articlesData = articlesUseCase
        .getDataArticles()
        .asLiveData()

    val blogsData = blogsUseCase
        .getDataBlogs()
        .asLiveData()

}