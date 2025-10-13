package rachman.forniandi.aerospaceflightnews.uiPresentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController= findNavController(R.id.nav_host_fragment_container)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.articlesFragment,
            R.id.blogsFragment,
            //R.id.favoriteContentsFragment
        )

        binding.bottomNavigationMain.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.articlesFragment -> showToolbarAndNavBottomBar()
                R.id.blogsFragment -> showToolbarAndNavBottomBar()
                else -> hideToolbarAndNavBottomBar()
            }
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