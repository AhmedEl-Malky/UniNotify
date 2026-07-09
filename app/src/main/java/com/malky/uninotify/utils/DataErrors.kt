package com.malky.uninotify.utils

import com.malky.uninotify.R

sealed interface DataErrors : ErrorType {
    enum class Remote : DataErrors {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNAUTHORIZED,
        UNKNOWN,
        NOT_FOUND;

        override fun toUiText(): UiText {
            val stringRes = when (this) {
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


    enum class Local : DataErrors {
        DISK_IO_ERROR, // For when the database file can't be read or written.
        DATABASE_CORRUPT, // For when the database file is malformed.
        QUERY_FAILED, // For when a specific query fails for syntax or other reasons.
        UNKNOWN;

        override fun toUiText(): UiText {
            val stringRes = when (this) {
                DISK_IO_ERROR -> R.string.LocalDiskIOError
                DATABASE_CORRUPT -> R.string.LocalDatabaseCorrupt
                QUERY_FAILED -> R.string.LocalQueryFailed
                UNKNOWN -> R.string.UnknownError
            }
            return UiText.StringResourceId(stringRes)
        }
    }
}