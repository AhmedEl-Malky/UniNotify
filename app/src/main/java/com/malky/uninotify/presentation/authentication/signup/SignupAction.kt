package com.malky.uninotify.presentation.authentication.signup

sealed interface SignupAction {
    data class OnFirstNameChange(val value: String) : SignupAction
    data object OnFirstNameValidate : SignupAction

    data class OnLastNameChange(val value: String) : SignupAction
    data object OnLastNameValidate : SignupAction

    data class OnEmailChange(val value: String) : SignupAction
    data object OnEmailValidate : SignupAction

    data class OnPasswordChange(val value: String) : SignupAction
    data object OnPasswordValidate : SignupAction

    data class OnConfirmPasswordChange(val value: String) : SignupAction
    data object OnConfirmPasswordValidate : SignupAction

    data class OnSignup(val onSuccess: () -> Unit) : SignupAction
}