package ru.itis.android.uprising26.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.itis.android.uprising26.domain.model.SongDetails
import ru.itis.android.uprising26.domain.repository.MusicRepository

class GetSongDetailsUseCaseTest {

    private val repository: MusicRepository = mockk()
    private val useCase = GetSongDetailsUseCase(repository)

    @Test
    fun `invoke returns song details and calls repository once`() = runTest {
        val songId = 10L
        val expected = SongDetails(
            id = songId,
            title = "Believer",
            artist = "Imagine Dragons",
            albumName = "Evolve",
            releaseDate = "2017",
            imageUrl = "image",
            geniusUrl = "url",
            description = "description"
        )

        coEvery { repository.getSongDetails(songId) } returns expected

        val actual = useCase(songId)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { repository.getSongDetails(songId) }
    }
}