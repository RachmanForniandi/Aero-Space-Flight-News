package rachman.forniandi.aerospaceflightnews.uiPresentation.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import rachman.forniandi.aerospaceflightnews.databinding.ActivitySettingsBinding

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    private var binding: ActivitySettingsBinding?=null
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        observeTheme()
        setupFunctionalSettings()

    }

    private fun observeTheme() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isDarkMode.collect { isDarkMode ->

                    binding?.switchChangeTheme?.isChecked = isDarkMode


                    AppCompatDelegate.setDefaultNightMode(
                        if (isDarkMode)
                            AppCompatDelegate.MODE_NIGHT_YES
                        else
                            AppCompatDelegate.MODE_NIGHT_NO
                    )
                }
            }
        }
    }


    private fun setupFunctionalSettings() {
        binding?.apply{

            switchChangeTheme.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setTheme(isChecked)
            }

            lineOptionSettingChangeLanguage.setOnClickListener {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }

            btnBackSetting.setOnClickListener {
                onBackPressed()
            }
        }
    }
}