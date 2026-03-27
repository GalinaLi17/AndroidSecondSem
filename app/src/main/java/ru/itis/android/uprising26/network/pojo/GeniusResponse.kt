package ru.itis.android.uprising26.network.pojo

import com.google.gson.annotations.SerializedName

data class GeniusResponse(
    @SerializedName(value = "meta")
    val meta: MetaResponse?,
    @SerializedName(value = "response")
    val response: SearchHits?,
)

data class MetaResponse(
    @SerializedName(value = "status")
    val status: Int?
)

data class SearchHits(
    @SerializedName(value = "hits")
    val hits: List<SongCommonInfo>?
)

data class SongCommonInfo(
    @SerializedName(value = "type")
    val type: String?,
    @SerializedName(value = "result")
    val result: SongData?,
)

data class SongData(
    @SerializedName(value = "id")
    val id: Long?,
    @SerializedName(value = "artist_names")
    val artist: String?,
    @SerializedName(value = "full_title")
    val fullTitle: String?,
    @SerializedName(value = "path")
    val lyricsUrl: String?,
    @SerializedName(value = "url")
    val geniusUrl: String?,
    @SerializedName(value = "header_image_url")
    val headerImageUrl: String?,
    @SerializedName(value = "header_image_thumbnail_url")
    val headerThumbnailUrl: String?
)
