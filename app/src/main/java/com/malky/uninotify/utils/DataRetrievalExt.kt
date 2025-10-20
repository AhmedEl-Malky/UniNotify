package com.malky.uninotify.utils

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import java.lang.Exception

suspend inline fun <T> retrieveFromFireStore(
    action: suspend () -> T
): Response<T, RemoteDataErrors> {
    return try {
        Response.Success(action())
    } catch (e: FirebaseFirestoreException) {
        Log.e("FirestoreWrapper", "Unexpected error: ${e.javaClass.simpleName} - ${e.message}", e)
        when (e.code) {
            FirebaseFirestoreException.Code.DEADLINE_EXCEEDED -> Response.Error(RemoteDataErrors.REQUEST_TIMEOUT)
            FirebaseFirestoreException.Code.NOT_FOUND -> Response.Error(RemoteDataErrors.NOT_FOUND)
            FirebaseFirestoreException.Code.INTERNAL -> Response.Error(RemoteDataErrors.SERVER)
            FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED -> Response.Error(RemoteDataErrors.TOO_MANY_REQUESTS)
            FirebaseFirestoreException.Code.UNAUTHENTICATED -> Response.Error(RemoteDataErrors.UNAUTHORIZED)
            FirebaseFirestoreException.Code.CANCELLED -> Response.Error(RemoteDataErrors.UNKNOWN)
            FirebaseFirestoreException.Code.UNKNOWN -> Response.Error(RemoteDataErrors.UNKNOWN)
            else -> Response.Error(RemoteDataErrors.UNKNOWN)
        }
    } catch (e: Exception) {
        Log.e("FirestoreWrapper", "Unexpected error: ${e.javaClass.simpleName} - ${e.message}", e)
        Response.Error(RemoteDataErrors.UNKNOWN)
    }
}