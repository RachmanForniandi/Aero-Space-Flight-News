package rachman.forniandi.core.domain.entity


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import rachman.forniandi.core.data.local.room.AuthorConverter

@Parcelize
@Entity(tableName = "contents_table")
data class Contents(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String?,
    @TypeConverters(AuthorConverter::class)
    val authors: List<AuthorContents?>?,
    val url: String?,
    val imageUrl: String?,
    val newsSite: String?,
    val summary: String?,
    val publishedAt: String?,
    val updatedAt: String?,
    val type: ContentType,
) : Parcelable

