package ru.itis.android.uprising26.domain.model

data class SongDetails(
    val id: Long,
    val title: String,
    val artist: String,
    val albumName: String?,
    val releaseDate: String?,
    val imageUrl: String?,
    val geniusUrl: String?,
    val description: String?,
    val lyrics: String? = null
)