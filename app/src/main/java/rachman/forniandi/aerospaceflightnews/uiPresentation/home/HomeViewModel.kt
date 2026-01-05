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

    /*val articlesPagingData = MutableLiveData<PagingData<Contents>>()
    val blogsPagingData = MutableLiveData<PagingData<Contents>>()*/


    val articlesData = articlesUseCase
        .getDataArticles()
        .asLiveData()

    val blogsData = blogsUseCase
        .getDataBlogs()
        .asLiveData()

    /*// ---- Articles ----
    fun refreshPagingArticles() {
        viewModelScope.launch {
            articlesUseCase.getPagingArticles()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    articlesPagingData.postValue(pagingData)
                }
        }
    }

    fun getDetailArticle(id: Int): Flow<RemoteResponse<Contents>> {
        return articlesUseCase.getDetailArticles(id)
    }

    // ---- Blogs ----
    fun refreshPagingBlogs() {
        viewModelScope.launch {
            blogsUseCase.getBlogs()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    blogsPagingData.postValue(pagingData)
                }
        }
    }

    fun getDetailBlog(id: Int): Flow<RemoteResponse<Contents>> {
        return blogsUseCase.getDetailBlogs(id)
    }*/
}