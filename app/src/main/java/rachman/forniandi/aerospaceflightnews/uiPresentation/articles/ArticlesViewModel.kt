package rachman.forniandi.aerospaceflightnews.uiPresentation.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.domain.usecase.ArticlesUseCase
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val articlesUseCase: ArticlesUseCase) : ViewModel() {

    val getArticles = MutableLiveData<PagingData<Contents>>()

    fun refreshPagingArticles() {
        viewModelScope.launch {
            articlesUseCase.getPagingArticles().cachedIn(viewModelScope).collect {
                getArticles.postValue(it)
            }
        }
    }

    /*val pagingArticles: Flow<PagingData<Contents>> =
        articlesUseCase.getArticles().cachedIn(viewModelScope)*/

}