package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import rachman.forniandi.core.domain.usecase.ArticlesUseCase
import javax.inject.Inject

@HiltViewModel
class DetailArticlesViewModel @Inject constructor(private val articleUseCase: ArticlesUseCase
): ViewModel(){

    private val articleId = MutableLiveData<Int>()

    fun setArticleId(id: Int?){
        articleId.value = id!!

    }

    val detailArticle by lazy {
        articleId.switchMap {
            articleUseCase.getDetailArticles(it).asLiveData()
        }
    }

}