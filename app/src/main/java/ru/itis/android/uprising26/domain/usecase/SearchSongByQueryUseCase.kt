package ru.itis.android.uprising26.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.android.uprising26.domain.model.MusicModel
import ru.itis.android.uprising26.domain.repository.MusicRepository

class SearchSongByQueryUseCase(private val musicRepository: MusicRepository) {

    suspend operator fun invoke(query: String): List<MusicModel> {
        if (query.isBlank()) return emptyList()

        return withContext(Dispatchers.IO) {
            musicRepository.searchByQuery(query)
        }
    }
}