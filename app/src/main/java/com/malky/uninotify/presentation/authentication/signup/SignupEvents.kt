package com.malky.uninotify.presentation.authentication.signup

import com.malky.uninotify.utils.UiText

sealed interface SignupEvents {
    data class OnError(val error: UiText) : SignupEvents
}