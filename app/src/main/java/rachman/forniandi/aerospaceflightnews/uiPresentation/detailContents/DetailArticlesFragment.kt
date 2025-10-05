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
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.FragmentDetailArticlesBinding
import rachman.forniandi.aerospaceflightnews.util.animateLoadingProcessData
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.Contents

import kotlin.getValue


class DetailArticlesFragment : Fragment() {

    private var _binding: FragmentDetailArticlesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailArticlesViewModel by viewModels()
    private var idContent: Int? =0
    private var detailContent: Contents? = null
    private var linkUrlWeb: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = DetailArticlesFragmentArgs.fromBundle(arguments as Bundle).articleDetails
        idContent = args.id

        if (savedInstanceState === null){
            viewModel.setArticleId(idContent)
        }

        showDetailArticles()
    }

    private fun showDetailArticles() {
        viewModel.setArticleId(idContent)
        viewModel.detailArticle.observe(viewLifecycleOwner,articleObserver)
    }

    private val articleObserver = Observer<RemoteResponse<Contents>>{ response->
        when(response){
            is RemoteResponse.Loading-> {
                applyLoadingStateDetail(true)
            }
            is RemoteResponse.Success->{
                applyLoadingStateDetail(false)
                detailContent = response.data
                binding.txtTitleContent.text = detailContent?.title
                binding.txtSummaryContent.text = detailContent?.summary
                binding.txtPublishedAt.text = detailContent?.publishedAt
                binding.txtUpdatedAt.text = detailContent?.updatedAt
                binding.txtAuthor.text = detailContent?.authors?.get(0)?.name
                binding.imgOfContent.load(detailContent?.imageUrl){
                    placeholder(R.drawable.place_holder)
                    error(R.drawable.place_holder)
                    crossfade(true)
                }
                linkUrlWeb = detailContent?.url

                binding.btnToDetailContentWeb.setOnClickListener {
                    val toDetailContentWeb = DetailBlogsFragmentDirections.actionDetailBlogsFragmentToDetailContentsWebviewActivity(linkUrlWeb)
                    findNavController().navigate(toDetailContentWeb)
                }

            }
            is RemoteResponse.Error->{
                applyLoadingStateDetail(false)

            }
        }
    }


    private fun applyLoadingStateDetail(onProcess:Boolean){

        binding.btnToDetailContentWeb.isEnabled =!onProcess

        if (onProcess){
            binding.detailLoadingMask.root.animateLoadingProcessData(true)
        }else{
            binding.detailLoadingMask.root.animateLoadingProcessData(false)
        }
    }

    companion object {

    }
}