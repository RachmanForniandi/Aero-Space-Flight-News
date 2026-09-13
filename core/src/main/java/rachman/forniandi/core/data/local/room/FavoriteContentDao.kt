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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteContent(contentEntity: FavoriteContentsEntity)

    @Delete
    suspend fun deleteFavoriteContent(content: FavoriteContentsEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_contents WHERE id = :id AND contentType = :type)")
    fun isFavoriteContent(id: Int, type: ContentType): Flow<Boolean>

    @Query("DELETE FROM favorite_contents")
    suspend fun deleteAllFavoriteContents()
}