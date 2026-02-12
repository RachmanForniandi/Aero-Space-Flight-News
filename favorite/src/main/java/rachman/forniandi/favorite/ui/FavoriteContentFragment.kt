package rachman.forniandi.favorite.ui

import android.app.AlertDialog.Builder
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.EntryPointAccessors
import rachman.forniandi.aerospaceflightnews.di.FavoriteContentModuleDependencies
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.utilRemote.NavigationProvider
import rachman.forniandi.core.utilRemote.toContentsDomain
import rachman.forniandi.favorite.R
import rachman.forniandi.favorite.adapter.FavoriteContentAdapter
import rachman.forniandi.favorite.databinding.FragmentFavoriteContentBinding
import rachman.forniandi.favorite.viewmodel.FavoriteContentViewModel
import rachman.forniandi.favorite.viewmodel.FavoriteContentViewModelFactory
import kotlin.getValue


class FavoriteContentFragment : Fragment() {

    private var _binding: FragmentFavoriteContentBinding? = null
    private val binding get() = _binding
    private var adapter: FavoriteContentAdapter? = null
    private lateinit var viewModel: FavoriteContentViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val deps = EntryPointAccessors.fromApplication(
            requireActivity().applicationContext,
            FavoriteContentModuleDependencies::class.java
        )
        val favoriteUseCase = deps.provideFavoriteContentUseCase()
        val factory = FavoriteContentViewModelFactory(favoriteUseCase)
        viewModel = ViewModelProvider(this, factory)[FavoriteContentViewModel::class.java]
    }

    private val navigationProvider: NavigationProvider by lazy {
        (requireActivity() as? NavigationProvider)
            ?: throw IllegalStateException(getString(R.string.host_activity_must_implement_navigation_provider))
    }

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
        setupButtonClearFavorite()
        setupRecyclerView()
        observeFavorites()
    }
    private fun setupButtonClearFavorite() {
        binding?.fabClearFavoriteContent?.setOnClickListener {
            checkBeforeClearAllFavoriteContents()
        }

    }

    private fun checkBeforeClearAllFavoriteContents() {
        Builder(requireActivity())
            .setTitle(getString(R.string.delete_all_favorites))
            .setMessage(getString(R.string.are_you_sure_do_you_want_to_delete_all_favorites))
            .setNegativeButton(getString(R.string.no), null)
            .setPositiveButton(getString(R.string.yes)) { arg0, arg1 ->
                viewModel.deleteAllFavorites()
                binding?.root?.let {
                    Snackbar.make(
                        it,
                        getString(R.string.all_favorites_deleted),
                        Snackbar.LENGTH_SHORT
                    )
                }
                    ?.show()
            }.create().show()
    }



    private fun setupRecyclerView() {
        adapter = FavoriteContentAdapter { favorite ->

            val contents = favorite.toContentsDomain()

            when (favorite.contentType) {
                ContentType.ARTICLE -> navigationProvider.openArticleDetails(contents)
                ContentType.BLOG -> navigationProvider.openBlogDetails(contents)
            }
        }
        binding?.rvFavorites?.adapter = adapter
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
                adapter?.submitList(favorites)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        binding?.rvFavorites?.adapter = null
        adapter = null
    }
}