package com.malky.uninotify.data.utils


import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.utils.AuthenticationErrors
import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.utils.ErrorType
import kotlinx.io.IOException
import java.net.SocketTimeoutException

suspend inline fun authenticate(
    action: suspend () -> AuthResult
): Response<FirebaseUser, ErrorType> {
    return try {
        val result = action()
        Response.Success(result.user!!)
    } catch (e: SocketTimeoutException) {
        Response.Error(DataErrors.Remote.REQUEST_TIMEOUT)
    } catch (e: IOException) {
        Response.Error(DataErrors.Remote.NO_INTERNET)
    } catch (e: FirebaseNetworkException) {
        Response.Error(DataErrors.Remote.NO_INTERNET)
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        Response.Error(AuthenticationErrors.Invalid_Credentials)
    } catch (e: FirebaseAuthInvalidUserException) {
        Response.Error(AuthenticationErrors.User_Not_Found)
    } catch (e: FirebaseAuthUserCollisionException) {
        Response.Error(AuthenticationErrors.Email_Already_Exists)
    }catch (e: Exception) {
        Response.Error(DataErrors.Remote.UNKNOWN)
    }
}