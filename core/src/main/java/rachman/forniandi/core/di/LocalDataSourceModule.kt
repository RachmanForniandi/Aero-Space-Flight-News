package rachman.forniandi.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.data.local.ContentsLocalDataSource
import rachman.forniandi.core.data.local.room.ContentsDao
import rachman.forniandi.core.data.local.room.FavoriteContentDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideContentsLocalDataSource(
        contentsDao: ContentsDao,
        favoriteDao: FavoriteContentDao
    ): ContentsLocalDataSource = ContentsLocalDataSource(contentsDao, favoriteDao)
}