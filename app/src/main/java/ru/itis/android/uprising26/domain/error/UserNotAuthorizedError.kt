package ru.itis.android.uprising26.domain.error

class UserNotAuthorizedError(
    message: String,
    cause: Throwable? = null,
) : Throwable(message, cause)