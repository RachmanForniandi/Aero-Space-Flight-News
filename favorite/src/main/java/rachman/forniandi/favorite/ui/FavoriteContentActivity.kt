package rachman.forniandi.favorite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import rachman.forniandi.favorite.R
import rachman.forniandi.favorite.databinding.ActivityFavoriteContentBinding

@AndroidEntryPoint
class FavoriteContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}