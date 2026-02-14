package rachman.forniandi.aerospaceflightnews.uiPresentation.articles

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import rachman.forniandi.core.adapters.ContentAdapter
import rachman.forniandi.core.adapters.LoadingStatePageAdapter
import rachman.forniandi.aerospaceflightnews.databinding.FragmentArticlesBinding
import rachman.forniandi.core.domain.entity.Contents

@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding
    private val viewModel: ArticlesViewModel by viewModels()
    private var contentAdapter: ContentAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListArticles()

        showDataArticles()
        viewModel.refreshPagingArticles()

        setSwipeRefreshDataBlogs()

        showRefreshArticles(true)

        binding?.btnReloadPageArticles?.setOnClickListener {
            contentAdapter?.retry()
        }
    }

    private fun setSwipeRefreshDataBlogs() {
        binding?.swipeRefreshArticles?.setOnRefreshListener {
            viewModel.refreshPagingArticles()
            hideShimmer()
        }
    }


    private fun setupListArticles() {
        contentAdapter = ContentAdapter { contents -> contents?.let { handleClickToDetail(it) } }
        binding?.listArticles?.adapter = contentAdapter?.withLoadStateFooter(
            footer = LoadingStatePageAdapter { contentAdapter?.retry() }
        )

        contentAdapter?.addLoadStateListener { loadStates ->
            if (loadStates.refresh is LoadState.Loading) {
                showRefreshArticles(true)
                showShimmer()
            } else {
                showRefreshArticles(false)
                hideShimmer()
                val errorState = loadStates.source.refresh as? LoadState.Error
                val endOfPaginationReached = loadStates.append.endOfPaginationReached

                if (errorState != null) {
                    showErrorState(true)
                    binding?.listArticles?.visibility = View.GONE
                }else if (endOfPaginationReached && contentAdapter?.itemCount == 0) {
                    showEmptyState()
                }else{
                    showErrorState(false)
                    binding?.listArticles?.visibility = View.VISIBLE
                }
            }
        }
        showShimmer()

    }

    private fun handleClickToDetail(contents: Contents) {
        val toDetailContent = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailsFragment(contents)
        findNavController().navigate(toDetailContent)
    }

    private fun showDataArticles() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getArticles.observe(viewLifecycleOwner) { pagingResult ->
                    contentAdapter?.submitData(lifecycle, pagingResult)
                    showRefreshArticles(false)
                }
            }
        }
        viewModel.refreshPagingArticles()

    }


    private fun showShimmer(){
        binding?.apply{
            shimmerFrameLayoutArticles.visibility = View.VISIBLE
            shimmerFrameLayoutArticles.startShimmer()
            listArticles.visibility = View.GONE
        }

    }
    private fun hideShimmer(){
        binding?.apply {
            shimmerFrameLayoutArticles.stopShimmer()
            shimmerFrameLayoutArticles.visibility = View.GONE
            listArticles.visibility = View.VISIBLE
        }
    }


    private fun showErrorState(show: Boolean) {
        binding?.apply {
            imgDataEmpty.visibility = if (show) View.VISIBLE else View.GONE
            txtLblArticlesNotAvailable.visibility = if (show) View.VISIBLE else View.GONE
            btnReloadPageArticles.visibility = if (show) View.VISIBLE else View.GONE
            btnReloadPageArticles.setOnClickListener {
                showDataArticles()
            }
        }

    }

    private fun showEmptyState() {
        binding?.apply{
            imgDataEmpty.visibility = View.VISIBLE
            txtLblArticlesNotAvailable.visibility = View.VISIBLE
            btnReloadPageArticles.visibility = View.GONE
        }
    }

    private fun showRefreshArticles(isRefreshing: Boolean) {
        binding?.apply {
            swipeRefreshArticles.isRefreshing = isRefreshing
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.listArticles?.adapter = null
        contentAdapter = null
        _binding = null
    }

}