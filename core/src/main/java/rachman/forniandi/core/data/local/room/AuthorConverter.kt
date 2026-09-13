package rachman.forniandi.core.data.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import rachman.forniandi.core.domain.entity.AuthorContents

class AuthorConverter {

    @TypeConverter
    fun fromAuthorList(authors: List<AuthorContents?>?): String? {
        return Gson().toJson(authors)
    }

    @TypeConverter
    fun toAuthorList(data: String?): List<AuthorContents?>? {
        if (data == null) return emptyList()
        val listType = object : TypeToken<List<AuthorContents?>?>() {}.type
        return Gson().fromJson(data, listType)
    }
}