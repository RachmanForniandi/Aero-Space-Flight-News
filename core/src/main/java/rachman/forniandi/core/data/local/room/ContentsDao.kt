package rachman.forniandi.core.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents

@Dao
interface ContentsDao {

    @Query("SELECT * FROM contents_table WHERE type = :type ORDER BY publishedAt ASC, id ASC")
    fun getContentsByType(type: ContentType): PagingSource<Int, Contents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContents(contents: List<Contents>)

    @Query("SELECT * FROM contents_table WHERE id = :id LIMIT 1")
    fun getContentById(id: Int): Flow<Contents?>

    @Query("DELETE FROM contents_table WHERE type = :type")
    suspend fun clearContentsByType(type: ContentType)

}