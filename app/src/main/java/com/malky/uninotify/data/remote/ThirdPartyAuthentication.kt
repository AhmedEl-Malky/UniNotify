package com.malky.uninotify.data.remote

import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.utils.ErrorType
import com.malky.uninotify.data.utils.Response

interface ThirdPartyAuthentication {
    suspend fun signInWithGoogle() : Response<FirebaseUser, ErrorType>
}