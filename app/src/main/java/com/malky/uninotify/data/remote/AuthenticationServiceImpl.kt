package com.malky.uninotify.data.remote


import androidx.credentials.GetCredentialRequest
import com.google.android.gms.tasks.Task
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.malky.uninotify.BuildConfig
import com.malky.uninotify.domain.authentication.AuthenticationService
import com.malky.uninotify.domain.authentication.ThirdPartyAuthentication
import com.malky.uninotify.utils.AuthenticationErrors
import com.malky.uninotify.utils.Response

class AuthenticationServiceImpl(
    private val auth: FirebaseAuth
) : AuthenticationService, ThirdPartyAuthentication {
    override suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Response<FirebaseUser, AuthenticationErrors> {
        lateinit var signupTask: Task<AuthResult>
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                signupTask = task
            }
        return if (signupTask.isSuccessful) Response.Success(auth.currentUser!!) else Response.Error(
            AuthenticationErrors.Something_Went_Wrong_In_SignUp
        )
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Response<FirebaseUser, AuthenticationErrors> {
        lateinit var signInTask: Task<AuthResult>
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                signInTask = task
            }
        return if (signInTask.isSuccessful) Response.Success(auth.currentUser!!) else Response.Error(
            AuthenticationErrors.Something_Went_Wrong_In_SignIn
        )
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun signInWithGoogle(): Response<FirebaseUser, AuthenticationErrors> {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.Web_client_id)
            .setFilterByAuthorizedAccounts(true)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
//        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(request.data)
        return firebaseAuthWithGoogle(googleIdTokenCredential.idToken)

    }

    private fun firebaseAuthWithGoogle(idToken: String): Response<FirebaseUser, AuthenticationErrors> {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        lateinit var authTask: Task<AuthResult>
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                authTask = task
            }
        return if (authTask.isSuccessful) Response.Success(auth.currentUser!!) else Response.Error(
            AuthenticationErrors.Something_Went_Wrong_In_SignIn_With_Google
        )
    }

}