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
import rachman.forniandi.core.domain.usecase.BlogsUseCase
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase
import javax.inject.Inject

@HiltViewModel
class DetailBlogsViewModel @Inject constructor(
    private val blogsUseCase: BlogsUseCase,
    private val favoriteContentUseCase: FavoriteContentUseCase
): ViewModel(){

    private val blogId = MutableLiveData<Int>()

    fun setBlogId(id: Int){
        blogId.value = id

    }

    val detailBlog by lazy {
        blogId.switchMap {
            blogsUseCase.getDetailBlogs(it).asLiveData()
        }
    }

    fun toggleFavoriteBlog(content: FavoriteContentsEntity, isFavorite: Boolean) {
        viewModelScope.launch {
            favoriteContentUseCase.updateFavoriteContent(content, isFavorite)
        }
    }

    fun isBlogFavorites(id: Int) =
        favoriteContentUseCase.isFavoriteContent(id, ContentType.BLOG).asLiveData()
}