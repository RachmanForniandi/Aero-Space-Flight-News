package rachman.forniandi.aerospaceflightnews.uiPresentation


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.utilRemote.NavigationProvider

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationProvider {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        val navHostMainFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostMainFragment.navController

        binding.bottomNavigationMain.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.articlesFragment,
                R.id.blogsFragment,
                R.id.favorite_navigation  ->{
                    showNavBottomBar()
                }
                else -> hideNavBottomBar()
            }

        }


    }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onSupportNavigateUp()

    }

    private fun showNavBottomBar(){
        binding.bottomNavigationMain.visibility = View.VISIBLE
    }

    private fun hideNavBottomBar(){
        binding.bottomNavigationMain.visibility = View.GONE
    }


    override fun openArticleDetails(contents: Contents) {
        val bundle = bundleOf("articleDetails" to contents)
        navController.navigate(R.id.articleDetailsFragment, bundle)
    }

    override fun openBlogDetails(contents: Contents) {
        val bundle = bundleOf("blogDetails" to contents)
        navController.navigate(R.id.detailBlogsFragment, bundle)
    }
}