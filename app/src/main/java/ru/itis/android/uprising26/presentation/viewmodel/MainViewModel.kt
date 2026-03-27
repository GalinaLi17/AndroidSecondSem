package ru.itis.android.uprising26.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.android.uprising26.domain.model.MusicModel
import ru.itis.android.uprising26.domain.usecase.SearchSongByQueryUseCase
import ru.itis.android.uprising26.utils.handler.GeneralExceptionHandler

class MainViewModel(
    private val searchSongsUseCase: SearchSongByQueryUseCase,
    private val exceptionHandler: GeneralExceptionHandler
) : ViewModel() {

    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        data class Success(val songs: List<MusicModel>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun searchSongs(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val songs = searchSongsUseCase(query)
                _uiState.value = UiState.Success(songs)
            } catch (e: Exception) {
                exceptionHandler.handleException(e)
                _uiState.value = UiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}