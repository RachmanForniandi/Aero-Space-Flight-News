package rachman.forniandi.aerospaceflightnews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.aerospaceflightnews.data.remote.response.RemoteSourceData
import rachman.forniandi.aerospaceflightnews.repositories.ContentsRepository
import rachman.forniandi.aerospaceflightnews.repositories.ContentsRepositoryImpl
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