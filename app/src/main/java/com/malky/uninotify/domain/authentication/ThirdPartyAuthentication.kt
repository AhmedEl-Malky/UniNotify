package com.malky.uninotify.domain.authentication

import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.utils.ErrorType
import com.malky.uninotify.utils.Response

interface ThirdPartyAuthentication {
    suspend fun signInWithGoogle() : Response<FirebaseUser, ErrorType>
}