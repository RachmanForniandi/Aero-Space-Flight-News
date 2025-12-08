package rachman.forniandi.aerospaceflightnews.uiPresentation


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment
import rachman.forniandi.aerospaceflightnews.uiPresentation.articles.ArticlesFragmentDirections
import rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.BlogsFragmentDirections
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

        val navHostMainFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostMainFragment.navController

        binding.bottomNavigationMain.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.articlesFragment,
                R.id.blogsFragment ->{
                    showToolbarAndNavBottomBar()
                }
                R.id.favorite_navigation->hideToolbarAndShowBottomNavForFavorite()
                else -> hideToolbarAndNavBottomBar()
            }
        }
        setupToolbarMainSetting()

    }

    private fun setupToolbarMainSetting() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId){
                R.id.action_settings->{
                    val toSettings = Intent(this, SettingsActivity::class.java)
                    startActivity(toSettings)
                    true
                }
                else -> super.onOptionsItemSelected(menuItem)
            }

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onSupportNavigateUp()

    }

    private fun showToolbarAndNavBottomBar(){
        binding.bottomNavigationMain.visibility = View.VISIBLE
        binding.toolbar.visibility = View.VISIBLE
    }

    private fun hideToolbarAndNavBottomBar(){
        binding.bottomNavigationMain.visibility = View.GONE
        binding.toolbar.visibility = View.GONE
    }
    private fun hideToolbarAndShowBottomNavForFavorite(){
        binding.toolbar.visibility = View.GONE
        binding.bottomNavigationMain.visibility = View.VISIBLE
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