package rachman.forniandi.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import rachman.forniandi.core.BuildConfig
import rachman.forniandi.core.data.local.room.ContentsDatabase
import rachman.forniandi.core.data.local.room.ContentsDatabase.Companion.MIGRATION_1_2

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabasePassphrase(
        @ApplicationContext context: Context
    ): ByteArray {
        return "FixedPassphraseForDevelopment123!@#".toByteArray()
    }

    @Provides
    @Singleton
    fun provideSupportFactory(
        @ApplicationContext context: Context,
        passphrase: ByteArray
    ): SupportFactory {
        SQLiteDatabase.loadLibs(context)
        return SupportFactory(passphrase)
    }

    @Provides
    @Singleton
    fun provideContentsDatabase(
        @ApplicationContext context: Context,
        supportFactory: SupportFactory
    ): ContentsDatabase {

        if (BuildConfig.DEBUG) {
            context.deleteDatabase("contents_database")
        }

        return Room.databaseBuilder(
            context,
            ContentsDatabase::class.java,
            "contents_database"
        )
            .openHelperFactory(supportFactory)
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration() // Tambahkan ini untuk development
            .build()
    }

    @Provides
    fun provideContentsDao(db: ContentsDatabase) = db.contentsDao()

    @Provides
    fun provideFavoriteContentDao(db: ContentsDatabase) = db.favoriteContentsDao()

    @Provides
    fun provideRemoteKeysDao(db: ContentsDatabase) = db.remoteKeysDao()
}