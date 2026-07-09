package com.malky.uninotify.data.utils

import com.malky.uninotify.utils.ErrorType

sealed interface Response<out D, out E : ErrorType> {
    data class Success<D>(val data: D) : Response<D, Nothing>
    data class Error<out E : ErrorType>(val error: E) : Response<Nothing, E>
}

inline fun <D, E : ErrorType> Response<D, E>.onSuccess(action: (D) -> Unit): Response<D, E> {
    return when (this) {
        is Response.Error -> this
        is Response.Success -> {
            action(data)
            this
        }
    }
}

inline fun <D, E : ErrorType> Response<D, E>.onError(action: (E) -> Unit): Response<D, E> {
    return when (this) {
        is Response.Error -> {
            action(error)
            this
        }

        is Response.Success -> this
    }
}

inline fun <D, E : ErrorType, R> Response<D, E>.map(map: (D) -> R): Response<R, E> {
    return when (this) {
        is Response.Error -> Response.Error(error)
        is Response.Success -> Response.Success(data = map(data))
    }
}