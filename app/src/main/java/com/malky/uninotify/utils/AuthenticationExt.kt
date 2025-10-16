package com.malky.uninotify.utils


import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import kotlinx.io.IOException
import java.net.SocketTimeoutException

suspend inline fun authenticate(
    action: suspend () -> AuthResult
): Response<FirebaseUser, ErrorType> {
    return try {
        val result = action()
        Response.Success(result.user!!)
    } catch (e: SocketTimeoutException) {
        Response.Error(RemoteDataErrors.REQUEST_TIMEOUT)
    } catch (e: IOException) {
        Response.Error(RemoteDataErrors.NO_INTERNET)
    } catch (e: FirebaseNetworkException) {
        Response.Error(RemoteDataErrors.NO_INTERNET)
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        Response.Error(AuthenticationErrors.Invalid_Credentials)
    } catch (e: FirebaseAuthInvalidUserException) {
        Response.Error(AuthenticationErrors.User_Not_Found)
    } catch (e: FirebaseAuthUserCollisionException) {
        Response.Error(AuthenticationErrors.Email_Already_Exists)
    }catch (e: Exception) {
        Response.Error(RemoteDataErrors.UNKNOWN)
    }
}