package com.malky.uninotify.presentation.authentication.login

import com.malky.uninotify.utils.UiText

sealed interface LoginEvents {
    data class OnError(val error: UiText) : LoginEvents
}