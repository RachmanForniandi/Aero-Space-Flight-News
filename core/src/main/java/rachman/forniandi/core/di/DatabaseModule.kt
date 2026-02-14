package rachman.forniandi.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.data.local.room.ContentsDatabase
import rachman.forniandi.core.data.local.room.ContentsDatabase.Companion.MIGRATION_1_2
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides @Singleton fun provideContentsDatabase(
        @ApplicationContext context: Context ): ContentsDatabase =
        Room.databaseBuilder(context,
            ContentsDatabase::class.java,
            "contents_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    @Provides fun provideContentsDao(db: ContentsDatabase) = db.contentsDao()
    @Provides fun provideFavoriteContentDao(db: ContentsDatabase) = db.favoriteContentsDao()
    @Provides fun provideRemoteKeysDao(db: ContentsDatabase) = db.remoteKeysDao()
}