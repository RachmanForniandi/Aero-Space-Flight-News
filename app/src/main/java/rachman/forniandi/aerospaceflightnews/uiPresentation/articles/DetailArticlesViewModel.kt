package rachman.forniandi.aerospaceflightnews.uiPresentation.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import rachman.forniandi.aerospaceflightnews.domain.useCase.ArticlesUseCase
import javax.inject.Inject

@HiltViewModel
class DetailArticlesViewModel @Inject constructor(private val articlesUseCase: ArticlesUseCase): ViewModel() {

    private val _idArticlesValue = MutableLiveData<Int>()

    fun setValueIdArticles(idContent:Int){
        _idArticlesValue.value =idContent
    }

    val detailArticles by lazy{
        _idArticlesValue.switchMap {
            articlesUseCase.getDetailArticles(it).asLiveData()
        }
    }

    
}