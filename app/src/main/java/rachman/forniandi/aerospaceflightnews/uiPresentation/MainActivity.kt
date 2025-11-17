package rachman.forniandi.aerospaceflightnews.uiPresentation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ActivityMainBinding
import rachman.forniandi.aerospaceflightnews.uiPresentation.articles.ArticlesFragmentDirections
import rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.BlogsFragmentDirections
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.utilRemote.toContentsDomain

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var splitInstallManager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController= findNavController(R.id.nav_host_fragment_container)

        binding.bottomNavigationMain.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.articlesFragment -> showToolbarAndNavBottomBar()
                R.id.blogsFragment -> showToolbarAndNavBottomBar()
                else -> hideToolbarAndNavBottomBar()
            }
        }
        splitInstallManager = SplitInstallManagerFactory.create(this)

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                return try {
                    navigateToFavorite()
                    true
                } catch (e: ClassNotFoundException) {
                    Toast.makeText(this, "Favorite module not installed!", Toast.LENGTH_SHORT).show()
                    false
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(this, "Favorite destination not found!", Toast.LENGTH_SHORT).show()
                    false
                } as Boolean
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun navigateToFavorite() {
        val moduleName = "favorite"

        if (splitInstallManager.installedModules.contains(moduleName)) {
            openFavoritePage()
        } else {
            installFavoriteModule(moduleName)
        }
    }

    private fun installFavoriteModule(moduleName: String) {
        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        Toast.makeText(this, "downloading module favorite...", Toast.LENGTH_SHORT).show()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                Toast.makeText(this, "Install favorite module successfully", Toast.LENGTH_SHORT).show()
                openFavoritePage()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Failed to install module: $exception", Toast.LENGTH_LONG).show()
            }
    }
    private fun openFavoritePage() {
        val navController = findNavController(R.id.nav_host_fragment_container)

        try {
            navController.navigate("favoriteContentFragment")
            hideToolbarAndNavBottomBar()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed navigate Favorite: ${e.message}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showToolbarAndNavBottomBar(){
        binding.bottomNavigationMain.visibility = View.VISIBLE
        binding.homeAppBarLayout.visibility = View.VISIBLE
    }

    private fun hideToolbarAndNavBottomBar(){
        binding.bottomNavigationMain.visibility = View.GONE
        binding.homeAppBarLayout.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onSupportNavigateUp()

    }
}