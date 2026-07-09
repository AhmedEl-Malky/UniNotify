package com.malky.uninotify.presentation.authentication.signup

interface SignupInteractionListener {
    fun onFirstNameChange(value: String)

    fun onFirstNameValidate()

    fun onLastNameChange(value: String)

    fun onLastNameValidate()

    fun onEmailChange(value: String)

    fun onEmailValidate()

    fun onPasswordChange(value: String)
    fun onPasswordValidate()

    fun onConfirmPasswordChange(value: String)

    fun onConfirmPasswordValidate()

    fun onSignup(onSuccess: () -> Unit)
}