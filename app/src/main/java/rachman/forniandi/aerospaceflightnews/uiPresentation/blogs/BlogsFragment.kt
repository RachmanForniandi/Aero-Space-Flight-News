package rachman.forniandi.aerospaceflightnews.uiPresentation.blogs

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import rachman.forniandi.aerospaceflightnews.adapters.ContentAdapter
import rachman.forniandi.aerospaceflightnews.adapters.LoadingStatePageAdapter
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.aerospaceflightnews.databinding.FragmentBlogsBinding
import rachman.forniandi.core.domain.entity.Contents

@AndroidEntryPoint
class BlogsFragment : Fragment() {

    private var _binding: FragmentBlogsBinding? = null
    private val binding get() = _binding
    private val viewModel: BlogsViewModel by viewModels()
    private lateinit var contentAdapter: ContentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlogsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListBlogs()
        setSwipeRefreshDataBlogs()
        showDataBlogs()

        showRefreshBlogs(true)

        binding?.btnReloadPage?.setOnClickListener {
            contentAdapter.retry()
        }
    }

    private fun setSwipeRefreshDataBlogs() {
        binding?.swipeRefreshBlogs?.setOnRefreshListener {
            viewModel.refreshPagingBlogs()
            hideShimmer()
        }
    }


    private fun setupListBlogs() {
        contentAdapter = ContentAdapter { contents -> contents?.let { handleClickToDetail(it) } }
        binding?.listBlogs?.adapter = contentAdapter.withLoadStateFooter(
            footer = LoadingStatePageAdapter { contentAdapter.retry() }
        )

        contentAdapter.addLoadStateListener { loadStates ->
            if (loadStates.refresh is LoadState.Loading) {
                showRefreshBlogs(true)
                showShimmer()
            } else {
                showRefreshBlogs(false)
                hideShimmer()
                val errorState = loadStates.source.refresh as? LoadState.Error
                val endOfPaginationReached = loadStates.append.endOfPaginationReached

                if (errorState != null) {
                    showErrorState(true)
                    binding?.listBlogs?.visibility = View.GONE
                } else if (endOfPaginationReached && contentAdapter.itemCount == 0) {
                    showEmptyState()
                } else {
                    showErrorState(false)
                    binding?.listBlogs?.visibility = View.VISIBLE
                }
            }
        }
        showShimmer()
    }

    private fun handleClickToDetail(contents: Contents) {
        val toDetailContent = BlogsFragmentDirections.actionBlogsFragmentToDetailBlogsFragment(contents)
        findNavController().navigate(toDetailContent)
    }

    private fun showDataBlogs() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getBlogs.observe(viewLifecycleOwner){ pagingResult ->
                    contentAdapter.submitData(lifecycle,pagingResult)
                    showRefreshBlogs(false)
                }
            }
        }
        viewModel.refreshPagingBlogs()
    }

    private fun showRefreshBlogs(isRefreshing: Boolean) {
        binding?.apply {
            swipeRefreshBlogs.isRefreshing = isRefreshing
        }
    }

    private fun showShimmer(){
        binding?.apply{
            shimmerFrameLayoutBlogs.visibility = View.VISIBLE
            shimmerFrameLayoutBlogs.startShimmer()
            listBlogs.visibility = View.GONE
        }

    }
    private fun hideShimmer(){
        binding?.apply{
            shimmerFrameLayoutBlogs.stopShimmer()
            shimmerFrameLayoutBlogs.visibility = View.GONE
            listBlogs.visibility = View.VISIBLE
        }

    }

    private fun showErrorState(show: Boolean) {
        binding?.apply{
            imgDataBlogsEmpty.visibility = if (show) View.VISIBLE else View.GONE
            txtLblBlogsNotAvailable.visibility = if (show) View.VISIBLE else View.GONE
            btnReloadPage.visibility = if (show) View.VISIBLE else View.GONE
            btnReloadPage.setOnClickListener {
                showDataBlogs()
            }
        }

    }

    private fun showEmptyState() {
        binding?.apply{
            imgDataBlogsEmpty.visibility = View.VISIBLE
            txtLblBlogsNotAvailable.visibility = View.VISIBLE
            btnReloadPage.visibility = View.GONE
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}