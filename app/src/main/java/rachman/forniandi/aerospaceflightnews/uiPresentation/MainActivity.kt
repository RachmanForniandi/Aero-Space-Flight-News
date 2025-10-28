package rachman.forniandi.aerospaceflightnews.uiPresentation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                try {
                    /*val intent =
                        Intent(this, Class.forName("rachman.forniandi.favorite.FavoriteContentActivity"))
                    startActivity(intent)*/
                    moveToFavorite()
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