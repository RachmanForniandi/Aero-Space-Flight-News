package rachman.forniandi.aerospaceflightnews.uiPresentation.blogs

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
import rachman.forniandi.aerospaceflightnews.databinding.FragmentBlogsBinding
import rachman.forniandi.core.domain.entity.Contents

@AndroidEntryPoint
class BlogsFragment : Fragment() {

    private var _binding: FragmentBlogsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BlogsViewModel by viewModels()
    private lateinit var contentAdapter: ContentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListBlogs()
        setSwipeRefreshDataBlogs()
        showDataBlogs()

        showRefreshBlogs(true)
    }

    private fun setSwipeRefreshDataBlogs() {
        binding.swipeRefreshBlogs.setOnRefreshListener {
            viewModel.refreshPagingBlogs()
            hideShimmer()
        }
    }


    private fun setupListBlogs() {
        contentAdapter = ContentAdapter()
        binding.listBlogs.adapter = contentAdapter
        contentAdapter.setOnClickListener(object : ContentAdapter.OnContentClickListener {
            override fun onClick(position: Int, idContent: Contents) {
                val toDetailContent = BlogsFragmentDirections.actionBlogsFragmentToDetailBlogsFragment(idContent)
                findNavController().navigate(toDetailContent)
            }
        })
        showShimmer()
    }

    private fun showDataBlogs() {
        viewModel.refreshPagingBlogs()
        viewModel.blogsObserve.observe(viewLifecycleOwner, blogsObserver)
    }


    private val blogsObserver= Observer<RemoteResponse<List<Contents>?>> { response ->
        when (response) {
            is RemoteResponse.Loading -> {
                showShimmer()
                showErrorState(false)
            }

            is RemoteResponse.Success -> {
                hideShimmer()
                showRefreshBlogs(false)
                val contents = response.data
                if (contents!=null){
                    if (contents.isEmpty()){
                        showErrorState(true)

                    }else{
                        contents.let { contentAdapter.setData(it) }
                        showErrorState(false)
                        binding.listBlogs.visibility = View.VISIBLE
                    }
                }


            }

            is RemoteResponse.Error -> {
                hideShimmer()
                showRefreshBlogs(false)
                showErrorState(true)
                binding.listBlogs.visibility = View.GONE
            }

        }

    }

    private fun showRefreshBlogs(isRefreshing: Boolean) {
        binding.apply {
            swipeRefreshBlogs.isRefreshing = isRefreshing
        }
    }

    private fun showShimmer(){
        binding.shimmerFrameLayoutBlogs.visibility = View.VISIBLE
        binding.shimmerFrameLayoutBlogs.startShimmer()
        //binding.listBlogs.visibility = View.GONE
    }
    private fun hideShimmer(){
        binding.shimmerFrameLayoutBlogs.stopShimmer()
        binding.shimmerFrameLayoutBlogs.visibility = View.GONE
        //binding.listBlogs.visibility = View.VISIBLE
    }

    private fun showErrorState(show: Boolean) {
        binding.imgDataBlogsEmpty.visibility = if (show) View.VISIBLE else View.GONE
        binding.txtLblBlogsNotAvailable.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnReloadPage.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnReloadPage.setOnClickListener {
            showDataBlogs()
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val EXTRA_BLOG = "extra_blog"
    }
}