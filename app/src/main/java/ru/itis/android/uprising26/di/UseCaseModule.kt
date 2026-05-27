package ru.itis.android.uprising26.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.android.uprising26.domain.repository.MusicRepository
import ru.itis.android.uprising26.domain.usecase.GetSongDetailsUseCase
import ru.itis.android.uprising26.domain.usecase.SearchSongByQueryUseCase
import ru.itis.android.uprising26.utils.handler.GeneralExceptionHandler
import ru.itis.android.uprising26.utils.handler.GeneralExceptionHandlerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchSongsUseCase(
        musicRepository: MusicRepository
    ): SearchSongByQueryUseCase {
        return SearchSongByQueryUseCase(musicRepository)
    }

    @Provides
    @Singleton
    fun provideGetSongDetailsUseCase(
        musicRepository: MusicRepository
    ): GetSongDetailsUseCase {
        return GetSongDetailsUseCase(musicRepository)
    }

    @Provides
    @Singleton
    fun provideGeneralExceptionHandler(
        userRepository: ru.itis.android.uprising26.domain.repository.UserRepository
    ): GeneralExceptionHandler {
        return GeneralExceptionHandlerImpl(userRepository)
    }
}