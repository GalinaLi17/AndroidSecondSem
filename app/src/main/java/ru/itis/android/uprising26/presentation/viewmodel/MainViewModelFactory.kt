package ru.itis.android.uprising26.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import ru.itis.android.uprising26.domain.usecase.SearchSongByQueryUseCase
import ru.itis.android.uprising26.utils.handler.GeneralExceptionHandler

class MainViewModelFactory(
    private val searchSongsUseCase: SearchSongByQueryUseCase,
    private val exceptionHandler: GeneralExceptionHandler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                searchSongsUseCase = searchSongsUseCase,
                exceptionHandler = exceptionHandler
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}