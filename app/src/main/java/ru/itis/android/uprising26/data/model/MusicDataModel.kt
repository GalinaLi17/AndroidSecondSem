package ru.itis.android.uprising26.data.model

data class MusicDataModel(
    val remoteId: String,
    val remoteTitle: String,
    val remoteArtist: String,
    val remoteAlbum: String? = null,
    val remoteYear: Int,
) {
    companion object {
        val EMPTY = MusicDataModel(
            remoteId = "",
            remoteTitle = "",
            remoteArtist = "",
            remoteAlbum = "",
            remoteYear = 0,
        )
    }
}
