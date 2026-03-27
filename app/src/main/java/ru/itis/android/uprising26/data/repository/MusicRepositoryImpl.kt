package ru.itis.android.uprising26.data.repository

import ru.itis.android.uprising26.data.mapper.MusicModelMapper
import ru.itis.android.uprising26.data.mapper.SongDetailsMapper
import ru.itis.android.uprising26.domain.model.MusicModel
import ru.itis.android.uprising26.domain.model.SongDetails
import ru.itis.android.uprising26.domain.repository.MusicRepository
import ru.itis.android.uprising26.network.GeniusApi
import ru.itis.android.uprising26.network.pojo.request.PostRequestData

class MusicRepositoryImpl(
    private val geniusApi: GeniusApi,
    private val musicModelMapper: MusicModelMapper,
    private val songDetailsMapper: SongDetailsMapper = SongDetailsMapper()
) : MusicRepository {


    override suspend fun searchByQuery(query: String): List<MusicModel> {
        val response = geniusApi.getSongDataByQuery(query = query)
        return musicModelMapper.map(response) ?: listOf()
    }

    override suspend fun pathParamSample(userId: String) {
        val response = geniusApi.pathParamsSample(userId)
    }

    override suspend fun postRequestExample(id: String) {
        val requestBody = PostRequestData(
            id = id,
            timestamp = System.currentTimeMillis().toString()
        )
        geniusApi.postRequestSample(requestBody)
    }

    override suspend fun getSongDetails(songId: Long): SongDetails? {
        val response = geniusApi.getSongDetails(songId)
        return songDetailsMapper.map(response)
    }
}