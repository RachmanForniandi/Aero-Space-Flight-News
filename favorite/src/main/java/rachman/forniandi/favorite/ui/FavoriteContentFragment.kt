package rachman.forniandi.favorite.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AnimRes
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import rachman.forniandi.aerospaceflightnews.di.FavoriteContentModuleDependencies
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.utilRemote.NavigationHelper
import rachman.forniandi.core.utilRemote.toContentsDomain
import rachman.forniandi.favorite.R
import rachman.forniandi.favorite.adapter.FavoriteContentAdapter
import rachman.forniandi.favorite.databinding.FragmentFavoriteContentBinding
import rachman.forniandi.favorite.viewmodel.FavoriteContentViewModel
import kotlin.getValue


@AndroidEntryPoint
class FavoriteContentFragment : Fragment() {

    private var _binding: FragmentFavoriteContentBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: FavoriteContentAdapter


    private val viewModel: FavoriteContentViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteContentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModel.showAllFavoriteContents.observe(viewLifecycleOwner) { favorites ->
            if (favorites.isNullOrEmpty()) {
                binding?.rvFavorites?.visibility = View.GONE
                binding?.favoriteDataNotAvailable?.visibility = View.VISIBLE
                binding?.txtLblFavoriteNotAvailable?.visibility = View.VISIBLE
            } else {
                binding?.rvFavorites?.visibility = View.VISIBLE
                binding?.favoriteDataNotAvailable?.visibility = View.GONE
                binding?.txtLblFavoriteNotAvailable?.visibility = View.GONE
                adapter.submitList(favorites)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = FavoriteContentAdapter { favorite ->

            val contents = favorite.toContentsDomain()

            val direction = when (favorite.contentType) {
                ContentType.ARTICLE ->
                    FavoriteContentFragmentDirections.actionFavoriteContentFragmentToArticleDetailsFragment(contents)
                ContentType.BLOG ->
                    FavoriteContentFragmentDirections.actionFavoriteContentFragmentToDetailBlogsFragment(contents)
            }
            findNavController().navigate(direction)
        }
        binding?.rvFavorites?.adapter = adapter
    }

    private fun setupToolbar() {
        binding?.toolbarFavorite?.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        binding?.toolbarFavorite?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_delete_all -> {
                    viewModel.deleteAllFavorites()
                    binding?.root?.let { Snackbar.make(it, "All favorites deleted", Snackbar.LENGTH_SHORT) }
                        ?.show()
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}