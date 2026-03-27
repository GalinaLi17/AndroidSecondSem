package ru.itis.android.uprising26.network.pojo

import com.google.gson.annotations.SerializedName

data class SongDetailsResponse(
    @SerializedName("response")
    val response: SongDetailsData?
)

data class SongDetailsData(
    @SerializedName("song")
    val song: SongDetails?
)

data class SongDetails(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("artist_names")
    val artistNames: String?,

    @SerializedName("album")
    val album: AlbumInfo?,

    @SerializedName("release_date_for_display")
    val releaseDate: String?,

    @SerializedName("lyrics_state")
    val lyricsState: String?,

    @SerializedName("song_art_image_url")
    val imageUrl: String?,

    @SerializedName("header_image_url")
    val headerImageUrl: String?,

    @SerializedName("url")
    val geniusUrl: String?,

    @SerializedName("description")
    val description: DescriptionInfo?
)

data class AlbumInfo(
    @SerializedName("name")
    val name: String?,

    @SerializedName("cover_art_url")
    val coverArtUrl: String?
)

data class DescriptionInfo(
    @SerializedName("html")
    val html: String?
)