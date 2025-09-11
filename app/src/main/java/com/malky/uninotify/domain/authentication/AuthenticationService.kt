package com.malky.uninotify.domain.authentication

import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.utils.AuthenticationErrors
import com.malky.uninotify.utils.Response

interface AuthenticationService {
    suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) : Response<FirebaseUser, AuthenticationErrors>

    suspend fun signIn(
        email: String,
        password: String
    ) : Response<FirebaseUser, AuthenticationErrors>

    suspend fun signOut()

}