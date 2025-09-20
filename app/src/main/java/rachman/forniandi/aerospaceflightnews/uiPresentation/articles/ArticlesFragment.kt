package rachman.forniandi.aerospaceflightnews.uiPresentation.articles

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.adapters.ContentAdapter
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.aerospaceflightnews.databinding.FragmentArticlesBinding
import rachman.forniandi.core.domain.entity.Contents

@AndroidEntryPoint
class ArticlesFragment : Fragment() {


    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArticlesViewModel by viewModels()
    private lateinit var contentAdapter: ContentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListArticles()

        showDataArticles()

        setSwipeRefreshDataBlogs()

        showRefreshArticles(true)
    }

    private fun setSwipeRefreshDataBlogs() {
        binding.swipeRefreshArticles.setOnRefreshListener {
            viewModel.obtainArticles()
            hideShimmer()
        }
    }


    private fun setupListArticles() {
        contentAdapter = ContentAdapter()
        binding.listArticles.adapter = contentAdapter
        contentAdapter.setOnClickListener(object : ContentAdapter.OnContentClickListener {
            override fun onClick(position: Int, idContent: Contents) {
                val toDetailContent = ArticlesFragmentDirections.actionArticlesFragmentToDetailContentsFragment(idContent)
                findNavController().navigate(toDetailContent)
            }
        })
        showShimmer()

    }

    private fun showDataArticles() {
        viewModel.obtainArticles()
        viewModel.articlesObserve.observe(viewLifecycleOwner, articlesObserver)
    }

    private val articlesObserver = Observer<RemoteResponse<List<Contents>?>> { response ->
        when (response) {

            is RemoteResponse.Loading -> {
                showShimmer()
                showErrorState(false)
                binding.listArticles.visibility = View.GONE
            }

            is RemoteResponse.Success -> {
                hideShimmer()
                showRefreshArticles(false)
                val contents = response.data
                if (contents!=null){
                    if (contents.isEmpty()){
                        showErrorState(true)
                        binding.listArticles.visibility = View.GONE
                    }else{
                        contents.let { contentAdapter.setData(it) }
                        showErrorState(false)
                        binding.listArticles.visibility = View.VISIBLE
                    }
                }


            }

            is RemoteResponse.Error -> {
                hideShimmer()
                showRefreshArticles(false)
                showErrorState(true)
                binding.listArticles.visibility = View.GONE
            }

        }

    }

    private fun showShimmer(){
        binding.shimmerFrameLayoutArticles.visibility = View.VISIBLE
        binding.shimmerFrameLayoutArticles.startShimmer()
        //binding.listArticles.visibility = View.GONE
    }
    private fun hideShimmer(){
        binding.shimmerFrameLayoutArticles.stopShimmer()
        binding.shimmerFrameLayoutArticles.visibility = View.GONE
        //binding.listArticles.visibility = View.VISIBLE
    }

    private fun showErrorState(show: Boolean) {
        binding.imgDataEmpty.visibility = if (show) View.VISIBLE else View.GONE
        binding.txtLblArticlesNotAvailable.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnReloadPageArticles.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnReloadPageArticles.setOnClickListener {
            showDataArticles()
        }
    }

    private fun showRefreshArticles(isRefreshing: Boolean) {
        binding.apply {
            swipeRefreshArticles.isRefreshing = isRefreshing
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}