package ru.itis.android.uprising26.data.mapper

import ru.itis.android.uprising26.domain.model.SongDetails
import ru.itis.android.uprising26.network.pojo.SongDetailsResponse

class SongDetailsMapper {

    fun map(response: SongDetailsResponse?): SongDetails? {
        val song = response?.response?.song ?: return null

        return SongDetails(
            id = song.id ?: 0L,
            title = song.title ?: "Unknown Title",
            artist = song.artistNames ?: "Unknown Artist",
            albumName = song.album?.name,
            releaseDate = song.releaseDate,
            imageUrl = song.imageUrl ?: song.headerImageUrl,
            geniusUrl = song.geniusUrl,
            description = song.description?.html
        )
    }
}