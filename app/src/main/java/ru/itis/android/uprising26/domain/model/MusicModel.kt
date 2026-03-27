package ru.itis.android.uprising26.domain.model

data class MusicModel(
    val id: Long,
    val title: String,
    val artist: String,
    val imageUrl: String? = null
)
