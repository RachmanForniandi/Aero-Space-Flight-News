package rachman.forniandi.core.data.network

import rachman.forniandi.core.data.remote.response.ResponseGeneral
import rachman.forniandi.core.data.remote.response.ResultsItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("articles/")
    suspend fun getArticlesPaging(
        @Query("limit") limit: Int? =null,
        @Query("offset") offset: Int? =null
    ): ResponseGeneral

    @GET("blogs/")
    suspend fun getBlogsPaging(
        @Query("limit") limit: Int?=null,
        @Query("offset") offset: Int?=null
    ): ResponseGeneral

    @GET("articles/{id}")
    suspend fun getDetailArticles(@Path("id")id:Int): ResultsItem

    @GET("blogs/{id}")
    suspend fun getDetailBlogs(@Path("id")id:Int): ResultsItem

}