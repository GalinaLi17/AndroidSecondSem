package ru.itis.android.uprising26.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import ru.itis.android.uprising26.domain.usecase.GetSongDetailsUseCase
import ru.itis.android.uprising26.utils.handler.GeneralExceptionHandler

class DetailViewModelFactory(
    private val getSongDetailsUseCase: GetSongDetailsUseCase,
    private val exceptionHandler: GeneralExceptionHandler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(
                getSongDetailsUseCase = getSongDetailsUseCase,
                exceptionHandler = exceptionHandler,
                savedStateHandle = extras.createSavedStateHandle()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}