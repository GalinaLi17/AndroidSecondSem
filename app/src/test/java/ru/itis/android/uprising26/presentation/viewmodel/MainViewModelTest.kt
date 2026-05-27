package ru.itis.android.uprising26.presentation.viewmodel

import MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import ru.itis.android.uprising26.domain.model.MusicModel
import ru.itis.android.uprising26.domain.usecase.SearchSongByQueryUseCase
import ru.itis.android.uprising26.firebase.AnalyticsLogger
import ru.itis.android.uprising26.utils.SettingsManager
import ru.itis.android.uprising26.utils.handler.GeneralExceptionHandler

class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val searchSongsUseCase: SearchSongByQueryUseCase = mockk()
    private val exceptionHandler: GeneralExceptionHandler = mockk(relaxed = true)
    private val settingsManager: SettingsManager = mockk()
    private val analyticsLogger: AnalyticsLogger = mockk(relaxed = true)

    private fun createViewModel(): MainViewModel {
        every { settingsManager.isInfoShown() } returns true

        return MainViewModel(
            searchSongsUseCase = searchSongsUseCase,
            exceptionHandler = exceptionHandler,
            settingsManager = settingsManager,
            analyticsLogger = analyticsLogger
        )
    }

    @Test
    fun `searchSongs puts songs into success state`() = runTest {
        val songs = listOf(
            MusicModel(
                id = 1L,
                title = "Believer",
                artist = "Imagine Dragons",
                imageUrl = "image"
            )
        )

        coEvery { searchSongsUseCase("Believer") } returns songs

        val viewModel = createViewModel()

        viewModel.searchSongs("Believer")

        Assert.assertEquals(
            MainViewModel.UiState.Success(songs),
            viewModel.uiState.value
        )
    }

    @Test
    fun `searchSongs shows error when use case returns empty list`() = runTest {
        coEvery { searchSongsUseCase("Unknown song") } returns emptyList()

        val viewModel = createViewModel()

        viewModel.searchSongs("Unknown song")

        Assert.assertEquals(
            MainViewModel.UiState.Error("Songs not found"),
            viewModel.uiState.value
        )
    }

    @Test
    fun `dismissInfoScreen hides info screen and logs event`() = runTest {
        every { settingsManager.isInfoShown() } returns false
        justRun { settingsManager.setInfoShown() }

        val viewModel = MainViewModel(
            searchSongsUseCase = searchSongsUseCase,
            exceptionHandler = exceptionHandler,
            settingsManager = settingsManager,
            analyticsLogger = analyticsLogger
        )

        viewModel.dismissInfoScreen()

        Assert.assertEquals(false, viewModel.showInfoScreen.value)
        verify(exactly = 1) { settingsManager.setInfoShown() }
        verify(exactly = 1) { analyticsLogger.logInfoScreenClosed() }
    }
}