package rachman.forniandi.core.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStoreCore by preferencesDataStore(
    name = "setting_theme_preferences"
)

@Singleton
class SettingPreferenceImpl @Inject constructor(
    @ApplicationContext context: Context
) : DataSourceReference {

    private val dataStore = context.dataStoreCore

    override fun getTheme(): Flow<Boolean> =
        dataStore.data.map { prefs ->
            prefs[THEME_DARK_MODE] ?: false
        }

    override suspend fun setTheme(isDarkModeThemeActive: Boolean) {
        dataStore.edit { prefs ->
            prefs[THEME_DARK_MODE] = isDarkModeThemeActive
        }
    }

    companion object {
        val THEME_DARK_MODE =
            booleanPreferencesKey("is_dark_mode_theme_active")
    }
}
//private val Context.dataStoreCore by preferencesDataStore(SETTING_THEME_PREFERENCES)
//class SettingPreferenceImpl(context: Context): DataSourceReference {
//    private val dataStore = context.dataStoreCore
//
//    override fun getTheme(): Flow<Boolean> = dataStore.data.map { preferences ->
//        preferences[THEME_DARK_MODE]?: false
//    }
//
//    override suspend fun setTheme(isDarkModeThemeActive: Boolean) {
//        dataStore.edit {preferences ->
//            preferences[THEME_DARK_MODE] = isDarkModeThemeActive
//        }
//    }
//
//    companion object{
//        val THEME_DARK_MODE = booleanPreferencesKey("is_dark_mode_theme_active")
//        const val SETTING_THEME_PREFERENCES = "setting_theme_preferences"
//        @Volatile
//        private var instance: SettingPreferenceImpl? = null
//
//        fun getInstance(context: Context) =
//            instance ?: synchronized(this) {
//                instance ?: SettingPreferenceImpl(context)
//            }.also {
//                instance = it
//            }
//
//    }
//}