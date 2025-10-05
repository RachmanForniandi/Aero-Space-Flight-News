package rachman.forniandi.aerospaceflightnews.uiPresentation.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.domain.useCase.ArticlesUseCase
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val articlesUseCase: ArticlesUseCase) : ViewModel() {

    private val getArticles = MutableLiveData<RemoteResponse<List<Contents>?>>()


    val articlesObserve: MutableLiveData<RemoteResponse<List<Contents>?>> get()= getArticles

    fun obtainArticles() = viewModelScope.launch {
        articlesUseCase.getArticles().collect { response ->
            getArticles.value = response as RemoteResponse<List<Contents>?>?
        }

    }

}