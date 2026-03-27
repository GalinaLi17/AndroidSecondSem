package ru.itis.android.uprising26.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.android.uprising26.domain.model.SongDetails
import ru.itis.android.uprising26.domain.repository.MusicRepository

class GetSongDetailsUseCase(
    private val musicRepository: MusicRepository
) {

    suspend operator fun invoke(songId: Long): SongDetails? {
        return withContext(Dispatchers.IO) {
            musicRepository.getSongDetails(songId)
        }
    }
}