package rachman.forniandi.core.data.local.room

import androidx.room.TypeConverter
import rachman.forniandi.core.domain.entity.ContentType

class ContentTypeConverter {

    @TypeConverter
    fun fromContentType(type: ContentType): String = type.name

    @TypeConverter
    fun toContentType(value: String): ContentType =
        ContentType.valueOf(value)
}