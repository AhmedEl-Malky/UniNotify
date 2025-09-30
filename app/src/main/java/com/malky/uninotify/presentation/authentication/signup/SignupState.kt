package com.malky.uninotify.presentation.authentication.signup

import com.malky.uninotify.utils.UiText

data class SignupState(
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val firstName: String = "",
    val firstNameValidation: UiText? = null,
    val lastName: String = "",
    val lastNameValidation: UiText? = null,
    val email: String = "",
    val emailValidation: UiText? = null,
    val password: String = "",
    val passwordValidation: UiText? = null,
    val confirmPassword: String = "",
    val confirmPasswordValidation: UiText? = null
)