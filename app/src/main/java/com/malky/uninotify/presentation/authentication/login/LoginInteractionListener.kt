package com.malky.uninotify.presentation.authentication.login

interface LoginInteractionListener {
    fun onEmailChange(value:String)

    fun onPasswordChange(value: String)

    fun onSignIn(onSuccess: () -> Unit)

    fun onSignInWithGoogle(onSuccess: () -> Unit)

    fun onEmailValidate()


}