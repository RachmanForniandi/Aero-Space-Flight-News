package rachman.forniandi.core.data.local.entity


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import rachman.forniandi.core.domain.entity.ContentType

@Parcelize
@Entity(tableName = "favorite_contents")
data class FavoriteContentsEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val imageUrl: String?,
    val newsSite: String?,
    val summary: String?,
    val publishedAt: String?,
    val updateAt: String?,
    val url: String?,
    val contentType: ContentType,
    var isFavorite: Boolean = false
): Parcelable
