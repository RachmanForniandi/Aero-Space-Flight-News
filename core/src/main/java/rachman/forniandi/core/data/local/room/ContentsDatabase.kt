package rachman.forniandi.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.local.entity.RemoteKeys
import rachman.forniandi.core.domain.entity.Contents

@Database(
    entities = [Contents::class, FavoriteContentsEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(AuthorConverter::class)
abstract class ContentsDatabase : RoomDatabase(){
    abstract fun contentsDao(): ContentsDao
    abstract fun favoriteContentsDao(): FavoriteContentDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}


