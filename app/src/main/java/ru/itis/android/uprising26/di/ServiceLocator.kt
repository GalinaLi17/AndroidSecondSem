package ru.itis.android.uprising26.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.android.uprising26.buildconfig.impl.BuildConfigProviderImpl
import ru.itis.android.uprising26.data.mapper.MusicModelMapper
import ru.itis.android.uprising26.data.repository.MusicRepositoryImpl
import ru.itis.android.uprising26.data.repository.UserRepositoryImpl
import ru.itis.android.uprising26.domain.repository.MusicRepository
import ru.itis.android.uprising26.domain.usecase.GetSongDetailsUseCase
import ru.itis.android.uprising26.domain.usecase.SearchSongByQueryUseCase
import ru.itis.android.uprising26.network.GeniusApi
import ru.itis.android.uprising26.network.getUnsafeOkHttpClientBuilder
import ru.itis.android.uprising26.network.interceptor.ApiKeyInterceptor
import ru.itis.android.uprising26.utils.handler.GeneralExceptionHandlerImpl
import java.util.concurrent.TimeUnit

object ServiceLocator {

    private val buildConfigProviderImpl = BuildConfigProviderImpl()

    private val apiKeyInterceptor = ApiKeyInterceptor(buildConfigProviderImpl)



    private val okHttpClient = OkHttpClient.Builder()
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(apiKeyInterceptor)
        .build()

    private val unsafeClientHttp = getUnsafeOkHttpClientBuilder()
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(apiKeyInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(buildConfigProviderImpl.getGeniusApiBaseUrl())
        .client(unsafeClientHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val geniusApi = retrofit.create(GeniusApi::class.java)

    fun getGeneralExceptionHandler() = GeneralExceptionHandlerImpl(
        userRepository = UserRepositoryImpl()
    )

    fun getGeniusApi() = geniusApi

    fun getMusicRepository(): MusicRepository {
        return MusicRepositoryImpl(
            geniusApi = getGeniusApi(),
            musicModelMapper = MusicModelMapper(),
        )
    }

    fun getSongDetailsUseCase(): GetSongDetailsUseCase {
        return GetSongDetailsUseCase(getMusicRepository())
    }

    fun searchSongByQueryUseCase(): SearchSongByQueryUseCase {
        return SearchSongByQueryUseCase(getMusicRepository())
    }


}