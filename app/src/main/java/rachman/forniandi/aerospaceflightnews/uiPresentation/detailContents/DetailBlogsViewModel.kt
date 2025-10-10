package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import rachman.forniandi.core.domain.usecase.BlogsUseCase
import javax.inject.Inject

@HiltViewModel
class DetailBlogsViewModel @Inject constructor(private val blogsUseCase: BlogsUseCase): ViewModel(){

    private val blogId = MutableLiveData<Int>()

    fun setBlogId(id: Int){
        blogId.value = id

    }

    val detailBlog by lazy {
        blogId.switchMap {
            blogsUseCase.getDetailBlogs(it).asLiveData()
        }
    }
}