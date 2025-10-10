package rachman.forniandi.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.data.remote.response.RemoteSourceData
import rachman.forniandi.core.repositories.ContentsRepository
import rachman.forniandi.core.repositories.ContentsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideContentsRepository(
        remoteSourceData: RemoteSourceData
    ): ContentsRepository {
        return ContentsRepositoryImpl(remoteSourceData)
    }

}