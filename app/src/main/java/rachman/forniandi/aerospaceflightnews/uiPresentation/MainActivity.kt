package rachman.forniandi.aerospaceflightnews.uiPresentation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ActivityMainBinding
import rachman.forniandi.aerospaceflightnews.uiPresentation.articles.ArticlesFragmentDirections
import rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.BlogsFragmentDirections
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.utilRemote.toContentsDomain
import rachman.forniandi.favorite.ui.FavoriteContentActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController= findNavController(R.id.nav_host_fragment_container)


        handleIntentNavigation(intent)
        binding.bottomNavigationMain.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.articlesFragment -> showToolbarAndNavBottomBar()
                R.id.blogsFragment -> showToolbarAndNavBottomBar()
                else -> hideToolbarAndNavBottomBar()
            }
        }

    }

    private fun handleIntentNavigation(intent: Intent) {
        val isFromFavorite = intent.getBooleanExtra("EXTRA_FROM_FAVORITE", false) ?: false
        val favorite = intent.getParcelableExtra<FavoriteContentsEntity>("EXTRA_FAVORITE_CONTENT")

        if (isFromFavorite && favorite != null) {

            when (favorite.contentType) {
                ContentType.ARTICLE -> {
                    val direction = ArticlesFragmentDirections
                        .actionArticlesFragmentToArticleDetailsFragment(
                            favorite.toContentsDomain()
                        )
                    navController.navigate(direction)

                }

                ContentType.BLOG -> {
                    val direction = BlogsFragmentDirections
                        .actionBlogsFragmentToDetailBlogsFragment(
                            favorite.toContentsDomain()
                        )
                    navController.navigate(direction)
                }

            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                try {
                    val intent =
                        Intent(this, FavoriteContentActivity::class.java)
                    startActivity(intent)
                    //moveToFavorite()
                } catch (e: ClassNotFoundException) {
                    Toast.makeText(this, "Feature Favorite not installed yet!", Toast.LENGTH_SHORT).show()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun moveToFavorite() {
        val intent = Intent(this, Class.forName("rachman.forniandi.favorite.FavoriteContentActivity"))
        startActivity(intent)
    }

    /*private fun installFavoriteModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val request = SplitInstallRequest.newBuilder()
            .addModule("favorite") // nama modul sesuai folder di project
            .build()

        // Mulai proses download modul dinamis
        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                // Modul berhasil dipasang
                startActivity(Intent().setClassName(packageName, "rachman.forniandi.favorite.FavoriteContentActivity"))
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }*/

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