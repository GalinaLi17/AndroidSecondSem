package ru.itis.android.uprising26.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.android.uprising26.data.mapper.MusicModelMapper
import ru.itis.android.uprising26.data.mapper.SongDetailsMapper
import ru.itis.android.uprising26.data.repository.MusicRepositoryImpl
import ru.itis.android.uprising26.data.repository.UserRepositoryImpl
import ru.itis.android.uprising26.domain.repository.MusicRepository
import ru.itis.android.uprising26.domain.repository.UserRepository
import ru.itis.android.uprising26.network.GeniusApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMusicModelMapper(): MusicModelMapper = MusicModelMapper()

    @Provides
    @Singleton
    fun provideSongDetailsMapper(): SongDetailsMapper = SongDetailsMapper()

    @Provides
    @Singleton
    fun provideMusicRepository(
        geniusApi: GeniusApi,
        musicModelMapper: MusicModelMapper,
        songDetailsMapper: SongDetailsMapper
    ): MusicRepository {
        return MusicRepositoryImpl(
            geniusApi = geniusApi,
            musicModelMapper = musicModelMapper,
            songDetailsMapper = songDetailsMapper
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }
}