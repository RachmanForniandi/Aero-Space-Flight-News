package rachman.forniandi.aerospaceflightnews.uiPresentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import rachman.forniandi.core.data.local.datasource.DataSourceReference
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataSourceReference: DataSourceReference
) : ViewModel() {
    val isDarkMode: Flow<Boolean> = dataSourceReference.getTheme()

    fun setTheme(isDark: Boolean) {
        viewModelScope.launch {
            dataSourceReference.setTheme(isDark)
        }
    }
}