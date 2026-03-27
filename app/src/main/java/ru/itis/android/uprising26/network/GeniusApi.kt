package ru.itis.android.uprising26.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import ru.itis.android.uprising26.network.pojo.GeniusResponse
import ru.itis.android.uprising26.network.pojo.SongDetailsResponse
import ru.itis.android.uprising26.network.pojo.request.PostRequestData
import java.io.File

interface GeniusApi {

    @GET("search")
    suspend fun getSongDataByQuery(
        @Query(value = "q") query: String,
    ): GeniusResponse

    @GET("songs/{id}")
    suspend fun getSongDetails(
        @Path("id") songId: Long
    ): SongDetailsResponse

    @GET("search/{id}/music")
    suspend fun pathParamsSample(
        @Path(value = "id") param: String
    )

    @POST("somePostRequest")
    suspend fun postRequestSample(
        @Body requestBody: PostRequestData
    )

    @Multipart
    @POST
    suspend fun multipartSample(
        @Part("file") file: File
    )
}