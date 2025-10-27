package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.FragmentDetailBlogsBinding
import rachman.forniandi.aerospaceflightnews.util.animateLoadingProcessData
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents
import kotlin.getValue

@AndroidEntryPoint
class DetailBlogsFragment : Fragment() {
    private var _binding: FragmentDetailBlogsBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailBlogsViewModel by viewModels()
    private var idContent: Int? =0
    private var detailContent: Contents? = null
    private var linkUrlWeb: String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBlogsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = DetailBlogsFragmentArgs.fromBundle(arguments as Bundle).blogDetails
        idContent = args.id

        if (savedInstanceState === null){
            idContent?.let { viewModel.setBlogId(it) }
        }

        binding?.detailToolbar?.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        showDetailBlogs()
    }

    private fun showDetailBlogs() {
        idContent?.let { viewModel.setBlogId(it) }
        viewModel.detailBlog.observe(viewLifecycleOwner,blogObserver)
    }

    private val blogObserver = Observer<RemoteResponse<Contents>>{ response->
        when(response){
            is RemoteResponse.Loading-> {
                applyLoadingStateDetail(true)
            }
            is RemoteResponse.Success->{
                applyLoadingStateDetail(false)
                detailContent = response.data
                binding?.apply {
                    txtTitleContent.text = detailContent?.title
                    txtSummaryContent.text = detailContent?.summary
                    txtPublishedAt.text = detailContent?.publishedAt
                    txtUpdatedAt.text = detailContent?.updatedAt
                    txtSource.text = detailContent?.newsSite
                    txtAuthor.text = detailContent?.authors?.firstOrNull()?.name

                    imgOfContent.load(detailContent?.imageUrl){
                        placeholder(R.drawable.place_holder)
                        //error(R.drawable.place_holder)
                        crossfade(true)
                    }

                    btnToDetailContentWeb.setOnClickListener {
                        val toDetailContentWeb = detailContent?.let { urlWeb -> DetailBlogsFragmentDirections.actionDetailBlogsFragmentToDetailContentsWebviewActivity(urlWeb) }
                        toDetailContentWeb?.let { directions -> findNavController().navigate(directions) }
                    }
                }

                detailContent?.let { linkUrlWeb = it.url!! }

            }
            is RemoteResponse.Error->{
                applyLoadingStateDetail(false)
                showSnackBarError("Detail Blog Error.")
            }
        }
    }

    private fun showSnackBarError(@Suppress("SameParameterValue")message: String) {
        binding?.let { Snackbar.make(it.detailBlogs,message, Snackbar.LENGTH_SHORT) }
            ?.setAction("Ok"){}
            ?.show()
    }


    private fun applyLoadingStateDetail(onProcess:Boolean){

        binding?.btnToDetailContentWeb?.isEnabled =!onProcess

        if (onProcess){
            binding?.detailLoadingMask?.root?.animateLoadingProcessData(true)
            binding?.contentDetail?.animateLoadingProcessData(false)
        }else{
            binding?.detailLoadingMask?.root?.animateLoadingProcessData(false)
            binding?.contentDetail?.animateLoadingProcessData(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}