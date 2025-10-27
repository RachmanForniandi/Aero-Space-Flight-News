package rachman.forniandi.core.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.data.local.ContentsLocalDataSource
import rachman.forniandi.core.data.local.room.ContentsDatabase
import rachman.forniandi.core.data.remote.response.RemoteSourceData
import rachman.forniandi.core.repositories.ContentsRepository
import rachman.forniandi.core.repositories.ContentsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindContentsRepository(
        impl: ContentsRepositoryImpl
    ): ContentsRepository

}