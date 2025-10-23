package rachman.forniandi.core.data.local


import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.local.room.ContentsDao
import rachman.forniandi.core.data.local.room.FavoriteContentDao
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents
import javax.inject.Singleton

@Singleton
class ContentsLocalDataSource (
    private val contentsDao: ContentsDao,
    private val favoriteContentDao: FavoriteContentDao
){

    fun getAllContents(type: ContentType): PagingSource<Int, Contents> =
        contentsDao.getContentsByType(type)

    suspend fun insertContents(contents: List<Contents>) =
        contentsDao.insertContents(contents)

    suspend fun clearContentsByType(type: ContentType) =
        contentsDao.clearContentsByType(type)

    suspend fun getContentById(id: Int): Flow<Contents?> =
        contentsDao.getContentById(id)

    fun getAllFavorites(type: String): Flow<List<FavoriteContentsEntity>> =
        favoriteContentDao.getFavorites(type)

    suspend fun insertFavoriteContent(contentEntity: FavoriteContentsEntity) =
        favoriteContentDao.insertFavoriteContent(contentEntity)

    suspend fun isFavoriteContent(idContent: Int): Flow<Boolean> =
        favoriteContentDao.isFavoriteContent(idContent)


    suspend fun updateFavoriteContent(idContent: Int, isFavorite: Boolean) =
        favoriteContentDao.updateFavoriteContent(idContent, isFavorite)

    fun deleteAllFavoriteContents() =
        favoriteContentDao.deleteAllFavoriteContents()

}