package rachman.forniandi.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.core.utilRemote.NavigationHelper
import rachman.forniandi.favorite.R
import rachman.forniandi.favorite.adapter.FavoriteContentAdapter
import rachman.forniandi.favorite.databinding.ActivityFavoriteContentBinding
import rachman.forniandi.favorite.viewmodel.FavoriteContentViewModel

@AndroidEntryPoint
class FavoriteContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteContentBinding
    private val viewModel: FavoriteContentViewModel by viewModels()
    private lateinit var adapter: FavoriteContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        observeFavorites()

    }

    private fun observeFavorites() {
        viewModel.showAllFavoriteContents.observe(this) { favorites ->
            if (favorites.isNullOrEmpty()) {
                binding.rvFavorites.visibility = View.GONE
                binding.favoriteDataNotAvailable.visibility = View.VISIBLE
                binding.txtLblFavoriteNotAvailable.visibility = View.VISIBLE
            } else {
                binding.rvFavorites.visibility = View.VISIBLE
                binding.favoriteDataNotAvailable.visibility = View.GONE
                binding.txtLblFavoriteNotAvailable.visibility = View.GONE
                adapter.submitList(favorites)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = FavoriteContentAdapter { favorite ->
            NavigationHelper.navigateToMain(this, favorite.id, favorite.contentType.name)
        }

        binding.rvFavorites.adapter = adapter
    }

    private fun setupToolbar() {
        binding.toolbarFavorite.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.toolbarFavorite.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_delete_all -> {
                    viewModel.deleteAllFavorites()
                    Snackbar.make(binding.root, "All favorites deleted", Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}