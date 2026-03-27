package ru.itis.android.uprising26.buildconfig.api

interface BuildConfigProvider {

    fun getGeniusApiBaseUrl(): String

    fun getGeniusApiKey(): String
}