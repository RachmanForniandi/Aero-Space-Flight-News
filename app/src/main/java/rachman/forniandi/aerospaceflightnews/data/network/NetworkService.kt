package rachman.forniandi.aerospaceflightnews.data.network

import rachman.forniandi.aerospaceflightnews.data.remote.response.ResponseGeneral
import rachman.forniandi.aerospaceflightnews.data.remote.response.ResultsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("articles")
    suspend fun getArticles(): ResponseGeneral

    @GET("blogs")
    suspend fun getBlogs(): ResponseGeneral

    @GET("reports")
    suspend fun getReports(): ResponseGeneral

    @GET("articles/{id}")
    suspend fun getDetailArticles(@Path("id")id:Int): ResultsItem

    @GET("blogs/{id}")
    suspend fun getDetailBlogs(@Path("id")id:Int): ResultsItem

    @GET("reports/{id}")
    suspend fun getDetailReports(@Path("id")id:Int): ResultsItem


}