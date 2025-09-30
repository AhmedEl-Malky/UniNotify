package com.malky.uninotify.utils

sealed interface ErrorType{
    fun toUiText(): UiText
}