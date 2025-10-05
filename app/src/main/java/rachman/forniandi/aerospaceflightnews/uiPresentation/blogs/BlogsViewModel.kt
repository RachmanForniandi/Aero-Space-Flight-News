package rachman.forniandi.aerospaceflightnews.uiPresentation.blogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.domain.useCase.BlogsUseCase
import javax.inject.Inject

@HiltViewModel
class BlogsViewModel @Inject constructor(private val blogsUseCase: BlogsUseCase) : ViewModel() {

    private val getBlogs = MutableLiveData<RemoteResponse<List<Contents>?>>()

    val blogsObserve: MutableLiveData<RemoteResponse<List<Contents>?>> get()= getBlogs

    fun obtainBlogs() = viewModelScope.launch {
        blogsUseCase.getBlogs().collect { response->
            getBlogs.value = response as RemoteResponse<List<Contents>?>?
        }
    }

}