package com.malky.uninotify.utils


import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import kotlinx.io.IOException
import java.net.SocketTimeoutException

suspend inline fun authenticate(
    action: suspend () -> Response<FirebaseUser, ErrorType>
): Response<FirebaseUser, ErrorType> {
    return try {
        action()
    } catch (e: SocketTimeoutException) {
        return Response.Error(RemoteError.REQUEST_TIMEOUT)
    } catch (e: IOException) {
        return Response.Error(RemoteError.NO_INTERNET)
    } catch (e: FirebaseNetworkException) {
        return Response.Error(RemoteError.NO_INTERNET)
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        return Response.Error(AuthenticationErrors.Invalid_Credentials)
    } catch (e: FirebaseAuthInvalidUserException) {
        return Response.Error(AuthenticationErrors.User_Not_Found)
    } catch (e: FirebaseAuthUserCollisionException) {
        return Response.Error(AuthenticationErrors.Email_Already_Exists)
    }catch (e: Exception) {
        Log.d("AuthExc", e.message.toString())
        return Response.Error(RemoteError.UNKNOWN)
    }
}