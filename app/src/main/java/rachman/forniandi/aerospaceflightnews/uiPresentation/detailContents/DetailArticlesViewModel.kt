package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.usecase.ArticlesUseCase
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase
import javax.inject.Inject

@HiltViewModel
class DetailArticlesViewModel @Inject constructor(
    private val articleUseCase: ArticlesUseCase,
    private val favoriteContentUseCase: FavoriteContentUseCase
): ViewModel(){

    private val articleId = MutableLiveData<Int>()

    fun setArticleId(id: Int?){
        articleId.value = id ?: return

    }

    val detailArticle by lazy {
        articleId.switchMap {
            articleUseCase.getDetailArticles(it).asLiveData()
        }
    }

    fun toggleFavoriteArticle(content: FavoriteContentsEntity, isFavorite: Boolean) {
        viewModelScope.launch {
            favoriteContentUseCase.updateFavoriteContent(content, isFavorite)
        }
    }

    fun isArticleFavorites(id: Int, type: ContentType) =
        favoriteContentUseCase.isFavoriteContent(id, type).asLiveData()
}