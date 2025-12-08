package rachman.forniandi.aerospaceflightnews.uiPresentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.FragmentBlogsBinding
import rachman.forniandi.aerospaceflightnews.databinding.FragmentHomeBinding
import rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.BlogsViewModel
import rachman.forniandi.core.adapters.CarrouselAdapter
import rachman.forniandi.core.adapters.ContentAdapter
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val viewModel: HomeViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showImageSliderArticles()
        showImageSliderBlogs()
    }



    private fun showImageSliderArticles() {
        val adapter = CarrouselAdapter{ contentType, contents ->
            navigateToDetailArticles(ContentType.ARTICLE,contents)
        }

        binding?.rvArticlesCarrousel?.adapter = adapter

        viewModel.articlesData.observe(viewLifecycleOwner){ response->
            when(response){
                is RemoteResponse.Loading -> {
                    showShimmerSliderArticles()
                }
                is RemoteResponse.Success -> {
                    hideShimmerSliderArticles()
                    adapter.submitList(response.data)

                }
                is RemoteResponse.Error -> {
                    hideShimmerSliderArticles()
                    binding?.imgDataEmpty1?.visibility = View.VISIBLE
                    binding?.txtNoDataArticlesAvailable?.visibility = View.VISIBLE
                    binding?.rvArticlesCarrousel?.visibility = View.GONE


                }
            }

        }
    }

    private fun showImageSliderBlogs() {
        val adapter = CarrouselAdapter{ contentType, contents ->
            navigateToDetailBlogs(ContentType.BLOG,contents)
        }

        binding?.rvBlogsCarrousel?.adapter = adapter

        viewModel.blogsData.observe(viewLifecycleOwner){ response->
            when(response){
                is RemoteResponse.Loading -> {
                    showShimmerSliderBlogs()
                }

                is RemoteResponse.Success -> {
                    hideShimmerSliderBlogs()
                    adapter.submitList(response.data)

                }
                is RemoteResponse.Error -> {
                    hideShimmerSliderBlogs()
                    binding?.imgDataEmpty2?.visibility = View.VISIBLE
                    binding?.txtNoDataBlogsAvailable?.visibility = View.VISIBLE
                    binding?.rvBlogsCarrousel?.visibility = View.GONE


                }
            }

        }


    }

    private fun navigateToDetailArticles(type: ContentType,contents: Contents) {
        val dataItem = HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(contents)
        findNavController().navigate(dataItem)
    }

    private fun navigateToDetailBlogs(type: ContentType,contents: Contents) {
        val dataItem = HomeFragmentDirections.actionHomeFragmentToDetailBlogsFragment(contents)
        findNavController().navigate(dataItem)
    }

    private fun showShimmerSliderArticles() {
        binding?.shimmerFrameLayoutCarrouselArticles?.startShimmer()
        binding?.shimmerFrameLayoutCarrouselArticles?.visibility = View.VISIBLE
        binding?.rvArticlesCarrousel?.visibility = View.GONE
    }

    private fun hideShimmerSliderArticles() {
        binding?.shimmerFrameLayoutCarrouselArticles?.stopShimmer()
        binding?.shimmerFrameLayoutCarrouselArticles?.visibility = View.INVISIBLE
        binding?.rvArticlesCarrousel?.visibility = View.VISIBLE

    }

    private fun showShimmerSliderBlogs() {
        binding?.shimmerFrameLayoutCarrouselBlogs?.startShimmer()
        binding?.shimmerFrameLayoutCarrouselBlogs?.visibility = View.VISIBLE
        binding?.rvBlogsCarrousel?.visibility = View.GONE
    }

    private fun hideShimmerSliderBlogs() {
        binding?.shimmerFrameLayoutCarrouselBlogs?.stopShimmer()
        binding?.shimmerFrameLayoutCarrouselBlogs?.visibility = View.INVISIBLE
        binding?.rvBlogsCarrousel?.visibility = View.VISIBLE

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}