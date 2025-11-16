package rachman.forniandi.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val favoriteContentUseCase: FavoriteContentUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteContentViewModel::class.java) -> FavoriteContentViewModel(
                favoriteContentUseCase
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }
}