package rachman.forniandi.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.local.entity.RemoteKeys
import rachman.forniandi.core.domain.entity.Contents

@Database(
    entities = [Contents::class, FavoriteContentsEntity::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)

@TypeConverters(AuthorConverter::class, ContentTypeConverter::class)
abstract class ContentsDatabase : RoomDatabase(){
    abstract fun contentsDao(): ContentsDao
    abstract fun favoriteContentsDao(): FavoriteContentDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object{
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `favorite_contents` (
                        `id` INTEGER NOT NULL PRIMARY KEY,
                        `title` TEXT,
                        `imageUrl` TEXT,
                        `newsSite` TEXT,
                        `summary` TEXT,
                        `publishedAt` TEXT,
                        `updateAt` TEXT,
                        `url` TEXT,
                        `contentType` TEXT NOT NULL,
                        `isFavorite` INTEGER NOT NULL DEFAULT 0
                    )
                """.trimIndent())
            }
        }
    }
}


