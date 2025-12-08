package rachman.forniandi.aerospaceflightnews.uiPresentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.domain.usecase.ArticlesUseCase
import rachman.forniandi.core.domain.usecase.BlogsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val articlesUseCase: ArticlesUseCase,
    private val blogsUseCase: BlogsUseCase
) : ViewModel() {

    /*val articlesPagingData = MutableLiveData<PagingData<Contents>>()
    val blogsPagingData = MutableLiveData<PagingData<Contents>>()*/


    val articlesData by lazy {
        articlesUseCase.getDataArticles().asLiveData()
    }

    val blogsData by lazy {
        blogsUseCase.getDataBlogs().asLiveData()
    }

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