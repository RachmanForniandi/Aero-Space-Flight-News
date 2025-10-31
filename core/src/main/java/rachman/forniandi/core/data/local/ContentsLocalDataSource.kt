package rachman.forniandi.core.data.local


import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.local.room.ContentsDao
import rachman.forniandi.core.data.local.room.FavoriteContentDao
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents
import javax.inject.Inject
import javax.inject.Singleton


class ContentsLocalDataSource @Inject constructor(
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

    fun getAllFavorites(type: ContentType): Flow<List<FavoriteContentsEntity>> =
        favoriteContentDao.getFavorites(type)

    suspend fun insertFavoriteContent(contentEntity: FavoriteContentsEntity) =
        favoriteContentDao.insertFavoriteContent(contentEntity)

    fun isFavoriteContent(id: Int, type: ContentType): Flow<Boolean> =
        favoriteContentDao.isFavoriteContent(id,type)


    suspend fun deleteFavoriteContent(contentEntity: FavoriteContentsEntity) =
        favoriteContentDao.deleteFavoriteContent(contentEntity)

    suspend fun deleteAllFavoriteContents() =
        favoriteContentDao.deleteAllFavoriteContents()

}