package ru.itis.android.uprising26.data.repository

import ru.itis.android.uprising26.domain.repository.UserRepository


class UserRepositoryImpl : UserRepository {

    override suspend fun refreshUserToken(): String {
        return "Sample test string"
    }
}