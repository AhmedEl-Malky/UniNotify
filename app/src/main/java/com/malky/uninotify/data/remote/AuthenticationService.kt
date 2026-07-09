package com.malky.uninotify.data.remote

import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.utils.ErrorType
import com.malky.uninotify.data.utils.Response

interface AuthenticationService {
    suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): Response<FirebaseUser, ErrorType>

    suspend fun signIn(
        email: String,
        password: String
    ): Response<FirebaseUser, ErrorType>

    suspend fun signOut()
}