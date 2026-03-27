package ru.itis.android.uprising26.domain.repository

interface UserRepository {

    suspend fun refreshUserToken(): String
}