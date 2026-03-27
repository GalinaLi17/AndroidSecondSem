package ru.itis.android.uprising26.buildconfig.impl

import ru.itis.android.uprising26.BuildConfig

import ru.itis.android.uprising26.buildconfig.api.BuildConfigProvider
class BuildConfigProviderImpl : BuildConfigProvider {

    override fun getGeniusApiBaseUrl(): String = BuildConfig.GENIUS_API_BASE_URL

    override fun getGeniusApiKey(): String {
        return BuildConfig.apiKey
    }
}