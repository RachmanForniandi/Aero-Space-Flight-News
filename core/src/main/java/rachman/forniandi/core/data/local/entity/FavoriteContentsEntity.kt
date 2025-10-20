package rachman.forniandi.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_contents")
data class FavoriteContentsEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val imageUrl: String?,
    val newsSite: String?,
    val summary: String?,
    val publishedAt: String?,
    val url: String?,
    val contentType: String,
    var isFavorite: Boolean = false
)
