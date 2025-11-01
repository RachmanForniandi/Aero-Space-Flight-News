package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.FragmentDetailArticlesBinding
import rachman.forniandi.aerospaceflightnews.util.animateLoadingProcessData
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents

import kotlin.getValue

@AndroidEntryPoint
class DetailArticlesFragment : Fragment() {

    private var _binding: FragmentDetailArticlesBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailArticlesViewModel by viewModels()
    private var idContent: Int? =0
    private var detailContent: Contents? = null
    private var linkUrlWeb: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailArticlesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = DetailArticlesFragmentArgs.fromBundle(arguments as Bundle).articleDetails
        idContent = args.id

        if (savedInstanceState === null){
            viewModel.setArticleId(idContent)
        }

        binding?.detailToolbar?.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        showDetailArticles()
        observeFavoriteArticleState()

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
                binding?.apply{
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
                        val toDetailContentWeb = detailContent?.let { urlWeb -> DetailArticlesFragmentDirections.actionArticleDetailsFragmentToDetailContentsWebviewActivity(urlWeb) }
                        toDetailContentWeb?.let { directions -> findNavController().navigate(directions) }
                    }
                }

                detailContent?.let { linkUrlWeb = it.url!! }


            }
            is RemoteResponse.Error->{
                applyLoadingStateDetail(false)
                showSnackBarError("Detail Article Error.")


            }

        }
    }

    private fun observeFavoriteArticleState() {
        idContent.let {id ->
            id?.let { viewModel.isArticleFavorites(it) }?.observe(viewLifecycleOwner) { isFavorite ->
                binding?.fabFavoriteContent?.imageTintList = getColorStateList(
                    requireActivity(),
                    if (isFavorite) R.color.yellow else R.color.white
                )

                binding?.fabFavoriteContent?.setOnClickListener {
                    detailContent?.let { content ->
                        val favEntity = FavoriteContentsEntity(
                            id = content.id ?: 0,
                            title = content.title ?: "",
                            imageUrl = content.imageUrl ?: "",
                            newsSite = content.newsSite ?: "",
                            summary = content.summary ?: "",
                            publishedAt = content.publishedAt ?: "",
                            updateAt = content.updatedAt ?: "",
                            url = content.url ?: "",
                            contentType = ContentType.ARTICLE
                        )
                        viewModel.toggleFavoriteArticle(favEntity, isFavorite)
                    }
                }
            }
        }
    }

    private fun showSnackBarError(@Suppress("SameParameterValue") message: String?) {
        binding?.let { Snackbar.make(it.detailArticles,message.toString(), Snackbar.LENGTH_SHORT) }
            ?.setAction("Ok"){}
            ?.show()
    }

    private fun applyLoadingStateDetail(onProcess: Boolean) {

        binding?.btnToDetailContentWeb?.isEnabled = !onProcess

        if (onProcess) {
            binding?.detailLoadingMask?.root?.animateLoadingProcessData(true)
            binding?.contentDetail?.animateLoadingProcessData(false)
        } else {
            binding?.detailLoadingMask?.root?.animateLoadingProcessData(false)
            binding?.contentDetail?.animateLoadingProcessData(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}