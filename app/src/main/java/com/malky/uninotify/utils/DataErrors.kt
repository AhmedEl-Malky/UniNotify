package com.malky.uninotify.utils

import com.malky.uninotify.R

enum class RemoteDataErrors : ErrorType {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER,
    SERIALIZATION,
    UNAUTHORIZED,
    UNKNOWN,
    NOT_FOUND;

    override fun toUiText(): UiText {
        val stringRes = when(this){
            REQUEST_TIMEOUT -> R.string.RequestTimeout
            TOO_MANY_REQUESTS -> R.string.TooManyRequests
            NO_INTERNET -> R.string.NoInternet
            SERVER -> R.string.ServerError
            SERIALIZATION -> R.string.SerializationError
            UNAUTHORIZED -> R.string.UnAuthorized
            UNKNOWN -> R.string.UnknownError
            NOT_FOUND -> R.string.NotFound
        }
        return UiText.StringResourceId(stringRes)
    }
}

//enum class LocalDataErrors : ErrorType {
//
//
//    override fun toUiText(): UiText {
//    }
//}