package com.malky.uninotify.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.malky.uninotify.domain.authentication.AuthenticationService
import com.malky.uninotify.domain.authentication.ThirdPartyAuthentication
import com.malky.uninotify.utils.AuthenticationErrors
import com.malky.uninotify.utils.ErrorType
import com.malky.uninotify.utils.Response
import com.malky.uninotify.utils.authenticate
import kotlinx.coroutines.tasks.await

class AuthenticationServiceImpl(
    private val auth: FirebaseAuth
) : AuthenticationService, ThirdPartyAuthentication {
    override suspend fun signUp(
        email: String,
        password: String,
    ): Response<FirebaseUser, ErrorType> {
        return authenticate {
            lateinit var response: Response<FirebaseUser, ErrorType>
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    response = if (task.isSuccessful)
                        Response.Success(auth.currentUser!!)
                    else
                        Response.Error(AuthenticationErrors.Something_Went_Wrong)
                }.await()
            response
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Response<FirebaseUser, ErrorType> {
        lateinit var response: Response<FirebaseUser, ErrorType>
        return authenticate {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    response = if (task.isSuccessful)
                        Response.Success(auth.currentUser!!)
                    else
                        Response.Error(AuthenticationErrors.Something_Went_Wrong)
                }.await()
            response
        }
    }

    override suspend fun signOut() {
        auth.signOut()
    }

//    override suspend fun signInWithGoogle(): Response<FirebaseUser, AuthenticationErrors> {
//        val googleIdOption = GetGoogleIdOption.Builder()
//            .setServerClientId(BuildConfig.Web_client_id)
//            .setFilterByAuthorizedAccounts(true)
//            .build()
//
//        val request = GetCredentialRequest.Builder()
//            .addCredentialOption(googleIdOption)
//            .build()
//        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(request.data)
//        return firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
//
//    }

    private suspend fun firebaseAuthWithGoogle(idToken: String): Response<FirebaseUser, ErrorType> {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        lateinit var response: Response<FirebaseUser, ErrorType>
        return authenticate {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    response = if (task.isSuccessful)
                        Response.Success(auth.currentUser!!)
                    else
                        Response.Error(AuthenticationErrors.Something_Went_Wrong)
                }.await()
            response
        }
    }
}