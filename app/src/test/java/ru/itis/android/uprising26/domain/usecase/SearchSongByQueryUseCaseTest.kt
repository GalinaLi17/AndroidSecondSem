package ru.itis.android.uprising26.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import ru.itis.android.uprising26.domain.model.MusicModel
import ru.itis.android.uprising26.domain.repository.MusicRepository

class   SearchSongByQueryUseCaseTest {

    private val repository: MusicRepository = mockk()
    private val useCase = SearchSongByQueryUseCase(repository)

    @Test
    fun `invoke returns songs and calls repository once`() = runTest {
        val query = "Imagine Dragons"
        val expected = listOf(
            MusicModel(
                id = 1L,
                title = "Believer",
                artist = "Imagine Dragons",
                imageUrl = "image"
            )
        )

        coEvery { repository.searchByQuery(query) } returns expected

        val actual = useCase(query)

        TestCase.assertEquals(expected, actual)
        coVerify(exactly = 1) { repository.searchByQuery(query) }
    }

    @Test
    fun `invoke returns empty list when query is blank`() = runTest {
        val actual = useCase("   ")

        TestCase.assertEquals(emptyList<MusicModel>(), actual)
        coVerify(exactly = 0) { repository.searchByQuery(any()) }
    }
}