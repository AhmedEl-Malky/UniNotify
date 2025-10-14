package com.malky.uninotify.data.remote

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.malky.uninotify.BuildConfig
import com.malky.uninotify.domain.authentication.AuthenticationService
import com.malky.uninotify.domain.authentication.ThirdPartyAuthentication
import com.malky.uninotify.utils.AuthenticationErrors
import com.malky.uninotify.utils.ErrorType
import com.malky.uninotify.utils.RemoteError
import com.malky.uninotify.utils.Response
import com.malky.uninotify.utils.authenticate
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val context: Context
) : AuthenticationService, ThirdPartyAuthentication {

    private val credentialManager = CredentialManager.create(context)

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
        credentialManager.clearCredentialState(
            ClearCredentialStateRequest()
        )
        auth.signOut()
    }

    override suspend fun signInWithGoogle(): Response<FirebaseUser, ErrorType> {
        val result = getCredentialRequest()
        return handleSignIn(result)
    }

    private suspend fun getCredentialRequest(): GetCredentialResponse {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(BuildConfig.Web_client_id)
                .setAutoSelectEnabled(false)
                .build()
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()
            return credentialManager.getCredential(request = request, context = context)
    }

    private suspend fun handleSignIn(result: GetCredentialResponse): Response<FirebaseUser, ErrorType> {
        lateinit var response: Response<FirebaseUser, ErrorType>
        val credential = result.credential
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
            return authenticate {
                auth.signInWithCredential(authCredential)
                    .addOnCompleteListener { task ->
                        response = if (task.isSuccessful)
                            Response.Success(auth.currentUser!!)
                        else
                            Response.Error(AuthenticationErrors.Something_Went_Wrong)
                    }.await()
                response
            }
        } else
            return Response.Error(error = RemoteError.UNKNOWN)
    }
}