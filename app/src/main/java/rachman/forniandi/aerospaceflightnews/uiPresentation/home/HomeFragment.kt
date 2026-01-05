package rachman.forniandi.aerospaceflightnews.uiPresentation.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.FragmentHomeBinding
import rachman.forniandi.aerospaceflightnews.uiPresentation.settings.SettingsActivity
import rachman.forniandi.core.adapters.CarrouselAdapter
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var articlesAdapter: CarrouselAdapter
    private lateinit var blogsAdapter: CarrouselAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        observeArticles()
        observeBlogs()
        setupToolbarMainSetting()
    }

    private fun setupAdapters() {
        articlesAdapter = CarrouselAdapter { contents ->
            navigateToDetailArticles(contents)
        }

        blogsAdapter = CarrouselAdapter { contents ->
            navigateToDetailBlogs(contents)
        }

        binding?.rvArticlesCarrousel?.adapter = articlesAdapter
        binding?.rvBlogsCarrousel?.adapter = blogsAdapter
    }


    private fun setupToolbarMainSetting() {
        binding?.toolbar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId){
                R.id.action_settings->{
                    val toSettings = Intent(requireActivity(), SettingsActivity::class.java)
                    startActivity(toSettings)
                    true
                }
                else -> super.onOptionsItemSelected(menuItem)
            }

        }
    }



    private fun observeArticles() {
        viewModel.articlesData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is RemoteResponse.Loading -> showShimmerSliderArticles()

                is RemoteResponse.Success -> {
                    hideShimmerSliderArticles()
                    articlesAdapter.submitList(response.data)

                    binding?.rvArticlesCarrousel?.visibility = View.VISIBLE
                    binding?.imgDataEmpty1?.visibility = View.GONE
                    binding?.txtNoDataArticlesAvailable?.visibility = View.GONE
                }

                is RemoteResponse.Error -> {
                    hideShimmerSliderArticles()
                    binding?.rvArticlesCarrousel?.visibility = View.GONE
                    binding?.imgDataEmpty1?.visibility = View.VISIBLE
                    binding?.txtNoDataArticlesAvailable?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observeBlogs() {
        viewModel.blogsData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is RemoteResponse.Loading -> showShimmerSliderBlogs()

                is RemoteResponse.Success -> {
                    hideShimmerSliderBlogs()
                    blogsAdapter.submitList(response.data)

                    binding?.rvBlogsCarrousel?.visibility = View.VISIBLE
                    binding?.imgDataEmpty2?.visibility = View.GONE
                    binding?.txtNoDataBlogsAvailable?.visibility = View.GONE
                }

                is RemoteResponse.Error -> {
                    hideShimmerSliderBlogs()
                    binding?.rvBlogsCarrousel?.visibility = View.GONE
                    binding?.imgDataEmpty2?.visibility = View.VISIBLE
                    binding?.txtNoDataBlogsAvailable?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun navigateToDetailArticles(contents: Contents) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(contents)
        findNavController().navigate(action)
    }

    private fun navigateToDetailBlogs(contents: Contents) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailBlogsFragment(contents)
        findNavController().navigate(action)
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