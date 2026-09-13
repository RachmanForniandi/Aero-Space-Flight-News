package rachman.forniandi.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.data.local.datasource.DataSourceReference
import rachman.forniandi.core.data.local.datasource.SettingPreferenceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    abstract fun bindDataSourceReference(
        impl: SettingPreferenceImpl
    ): DataSourceReference
}