package rachman.forniandi.core.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.BuildConfig
import rachman.forniandi.core.data.local.room.ContentsDatabase
import rachman.forniandi.core.data.local.room.ContentsDatabase.Companion.MIGRATION_1_2
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideContentsDatabase(
        @ApplicationContext context: Context
    ): ContentsDatabase {
        val builder = Room.databaseBuilder(
            context,
            ContentsDatabase::class.java,
            "contents_database"
        ).addMigrations(ContentsDatabase.MIGRATION_1_2)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d("RoomDB", "Database created: contents_database")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.d("RoomDB", "Database opened successfully")
                }
            })

        return if (BuildConfig.DEBUG) {
            builder.fallbackToDestructiveMigration().build()
        } else {
            builder.build()
        }
    }

    @Provides
    fun provideContentsDao(db: ContentsDatabase) = db.contentsDao()

    @Provides
    fun provideFavoriteContentDao(db: ContentsDatabase) = db.favoriteContentsDao()

    @Provides
    fun provideRemoteKeysDao(db: ContentsDatabase) = db.remoteKeysDao()
}