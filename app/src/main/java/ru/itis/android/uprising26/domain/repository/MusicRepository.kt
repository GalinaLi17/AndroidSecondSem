package ru.itis.android.uprising26.domain.repository

import ru.itis.android.uprising26.domain.model.MusicModel
import ru.itis.android.uprising26.domain.model.SongDetails

interface MusicRepository {

    suspend fun searchByQuery(query: String): List<MusicModel>
    suspend fun getSongDetails(songId: Long): SongDetails?

    suspend fun pathParamSample(userId: String)

    suspend fun postRequestExample(id: String)
}