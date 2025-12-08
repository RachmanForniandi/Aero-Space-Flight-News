package rachman.forniandi.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase
import javax.inject.Inject


class FavoriteContentViewModel @Inject constructor(
    private val favoriteContentUseCase: FavoriteContentUseCase
): ViewModel(){


    val showAllFavoriteContents: LiveData<List<FavoriteContentsEntity>> = combine(
        favoriteContentUseCase.getFavoriteContents(ContentType.ARTICLE),
        favoriteContentUseCase.getFavoriteContents(ContentType.BLOG)
    ) { articles, blogs ->
        (articles + blogs).sortedByDescending { it.id }
    }.asLiveData()


    fun deleteAllFavorites() {
        viewModelScope.launch {
            favoriteContentUseCase.deleteAllFavoriteContents()
        }
    }

}