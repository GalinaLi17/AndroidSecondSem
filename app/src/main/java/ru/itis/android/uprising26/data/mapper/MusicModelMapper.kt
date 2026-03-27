package ru.itis.android.uprising26.data.mapper

import ru.itis.android.uprising26.domain.model.MusicModel
import ru.itis.android.uprising26.network.pojo.GeniusResponse

class MusicModelMapper {

    fun map(input: GeniusResponse): List<MusicModel>? {
        val hitsList = input.response?.hits?.map { it.result }
        return hitsList?.map { songData ->
            MusicModel(
                id = songData?.id.orZero(),
                artist = songData?.artist.orEmpty(),
                title = songData?.fullTitle.orEmpty(),
                imageUrl = songData?.headerThumbnailUrl ?: songData?.headerImageUrl
            )
        }
    }

    private fun Long?.orZero(): Long = this ?: 0L
}