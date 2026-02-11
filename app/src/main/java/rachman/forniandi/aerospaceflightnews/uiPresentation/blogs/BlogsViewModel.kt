package rachman.forniandi.aerospaceflightnews.uiPresentation.blogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.domain.usecase.BlogsUseCase
import javax.inject.Inject

@HiltViewModel
class BlogsViewModel @Inject constructor(private val blogsUseCase: BlogsUseCase) : ViewModel() {

    val getBlogs = MutableLiveData<PagingData<Contents>>()

    fun refreshPagingBlogs() = viewModelScope.launch {
        blogsUseCase.getBlogs().cachedIn(viewModelScope).collect {
            getBlogs.postValue(it)
        }
    }

}