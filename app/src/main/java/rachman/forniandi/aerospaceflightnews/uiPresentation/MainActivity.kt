package rachman.forniandi.aerospaceflightnews.uiPresentation


import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.dynamicfeatures.DynamicIncludeGraphNavigator
import androidx.navigation.dynamicfeatures.DynamicInstallManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.play.core.splitinstall.SplitInstallHelper
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ActivityMainBinding
import rachman.forniandi.aerospaceflightnews.uiPresentation.articles.ArticlesFragmentDirections
import rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.BlogsFragmentDirections
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.utilRemote.NavigationProvider
import android.os.Handler

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationProvider {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var splitInstallManager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController= findNavController(R.id.nav_host_fragment_container)
        splitInstallManager = SplitInstallManagerFactory.create(this)

        setupDynamicNavigation()

        binding.bottomNavigationMain.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.articlesFragment -> showToolbarAndNavBottomBar()
                R.id.blogsFragment -> showToolbarAndNavBottomBar()
                else -> hideToolbarAndNavBottomBar()
            }
        }
    }

    private fun setupDynamicNavigation() {
        val dynamicInstallManager = DynamicInstallManager(
            context = this,
            splitInstallManager = splitInstallManager
        )

        val dynamicNavigator = DynamicIncludeGraphNavigator(
            context = this,
            navigatorProvider = navController.navigatorProvider,
            navInflater = navController.navInflater,
            installManager = dynamicInstallManager
        )

        navController.navigatorProvider.addNavigator(dynamicNavigator)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                navigateToFavorite()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun navigateToFavorite() {
        val moduleName = "favorite"

        Log.d("DynamicModule", "Installed modules: ${splitInstallManager.installedModules}")
        if (splitInstallManager.installedModules.contains(moduleName)) {
            Log.d("DynamicModule", "Module already installed, navigating...")
            openFavoritePage()
        } else {
            Log.d("DynamicModule", "Module not installed, starting download...")
            installFavoriteModule(moduleName)
        }
    }

    private fun installFavoriteModule(moduleName: String) {
        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        Toast.makeText(this, "downloading module favorite...", Toast.LENGTH_SHORT).show()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {sessionId ->
                Log.d("DynamicModule", "Install success: $sessionId")
                SplitInstallHelper.updateAppInfo(this)
                Toast.makeText(this, "Install favorite module successfully", Toast.LENGTH_SHORT).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    openFavoritePage()
                }, 1000)
            }
            .addOnFailureListener { exception ->
                Log.e("DynamicModule", "Install failed: $exception")
                Toast.makeText(this, "Failed to install module: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }
    private fun openFavoritePage() {
        /*val navToFavorite= NavContentDirections.actionGlobalFavoriteContentFragment()
        navController.navigate(navToFavorite)
        hideToolbarAndNavBottomBar()*/
        try {

            val deepLink = NavDeepLinkRequest.Builder
                .fromUri("aerospaceflightnews://favorite".toUri())
                .build()
            navController.navigate(deepLink)
            hideToolbarAndNavBottomBar()
        } catch (e: Exception) {
            Log.e("Navigation", "Failed to navigate to favorite: ${e.message}", e)
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


    override fun openArticleDetails(contents: Contents) {
        val action = ArticlesFragmentDirections
            .actionArticlesFragmentToArticleDetailsFragment(contents)
        navController.navigate(action)
    }

    override fun openBlogDetails(contents: Contents) {
        val action = BlogsFragmentDirections
            .actionBlogsFragmentToDetailBlogsFragment(contents)
        navController.navigate(action)
    }
}