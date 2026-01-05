package rachman.forniandi.aerospaceflightnews.apps

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.play.core.splitcompat.SplitCompat
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import rachman.forniandi.core.data.local.datasource.SettingPreferenceImpl

@HiltAndroidApp
class MyApplication: Application() {


    @Inject
    lateinit var settingPreference: SettingPreferenceImpl

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).launch {
            settingPreference.getTheme().collect { isDarkMode ->
                AppCompatDelegate.setDefaultNightMode(
                    if (isDarkMode)
                        AppCompatDelegate.MODE_NIGHT_YES
                    else
                        AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}