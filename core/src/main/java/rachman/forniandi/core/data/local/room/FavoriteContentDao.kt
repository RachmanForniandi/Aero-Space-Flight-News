package rachman.forniandi.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.domain.entity.ContentType

@Dao
interface FavoriteContentDao {

    @Query("SELECT * FROM favorite_contents WHERE contentType = :type ORDER BY id DESC")
    fun getFavorites(type: ContentType): Flow<List<FavoriteContentsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteContent(contentEntity: FavoriteContentsEntity)

    @Query("SELECT COUNT(*) FROM favorite_contents WHERE id = :idContent AND isFavorite = 1")
    fun isFavoriteContent(idContent: Int): Flow<Boolean>

    @Query("UPDATE favorite_contents SET isFavorite = :isFavorite WHERE id = :idContent")
    suspend fun updateFavoriteContent(idContent: Int, isFavorite: Boolean)

    @Query("DELETE FROM favorite_contents")
    suspend fun deleteAllFavoriteContents()
}