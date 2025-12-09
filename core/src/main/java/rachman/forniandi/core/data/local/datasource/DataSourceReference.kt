package rachman.forniandi.core.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface DataSourceReference {

    fun getTheme():Flow<Boolean>

    suspend fun setTheme(isDarkModeThemeActive:Boolean)

}