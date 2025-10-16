package com.malky.uninotify.presentation.authentication.login

import com.malky.uninotify.utils.UiText

data class LoginState(
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val isGoogleSignInLoading: Boolean = false,
    val email: String = "",
    val emailValidation: UiText? = null,
    val password: String = "",
)