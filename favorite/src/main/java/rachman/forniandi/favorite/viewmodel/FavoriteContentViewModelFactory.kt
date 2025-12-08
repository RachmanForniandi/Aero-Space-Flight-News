package rachman.forniandi.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase
import javax.inject.Inject

class FavoriteContentViewModelFactory @Inject constructor(
    private val favoriteContentUseCase: FavoriteContentUseCase
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteContentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteContentViewModel(favoriteContentUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}