package ru.itis.android.uprising26.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.android.uprising26.domain.model.SongDetails
import ru.itis.android.uprising26.domain.usecase.GetSongDetailsUseCase
import ru.itis.android.uprising26.utils.handler.GeneralExceptionHandler

class DetailViewModel(
    private val getSongDetailsUseCase: GetSongDetailsUseCase,
    private val exceptionHandler: GeneralExceptionHandler,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val song: SongDetails) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val songId: Long = savedStateHandle["songId"] ?: 0L

    init {
        loadSongDetails()
    }

    fun loadSongDetails() {
        if (songId == 0L) {
            _uiState.value = UiState.Error("Invalid song ID")
            return
        }

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val song = getSongDetailsUseCase(songId)  
                if (song != null) {
                    _uiState.value = UiState.Success(song)
                } else {
                    _uiState.value = UiState.Error("Song not found")
                }
            } catch (e: Exception) {
                exceptionHandler.handleException(e)
                _uiState.value = UiState.Error(e.message ?: "Failed to load song details")
            }
        }
    }

    fun retry() {
        loadSongDetails()
    }
}