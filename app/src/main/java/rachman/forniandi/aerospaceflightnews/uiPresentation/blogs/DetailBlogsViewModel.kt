package rachman.forniandi.aerospaceflightnews.uiPresentation.blogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import rachman.forniandi.aerospaceflightnews.domain.useCase.BlogsUseCase
import javax.inject.Inject

@HiltViewModel
class DetailBlogsViewModel @Inject constructor(private val blogsUseCase: BlogsUseCase): ViewModel(){

    private val _idBlogsValue = MutableLiveData<Int>()

    fun setValueIdBlogs(idContent:Int){
        _idBlogsValue.value =idContent
    }

    val detailBlogs by lazy{
        _idBlogsValue.switchMap {
            blogsUseCase.getDetailBlogs(it).asLiveData()
        }
    }
    
}