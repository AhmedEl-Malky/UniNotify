package com.malky.uninotify.data.utils

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseCorruptException
import android.database.sqlite.SQLiteDiskIOException
import androidx.sqlite.SQLiteException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.malky.uninotify.utils.DataErrors
import java.lang.Exception

suspend inline fun <T> retrieveFromFireStore(
    action: suspend () -> T
): Response<T, DataErrors.Remote> {
    return try {
        Response.Success(action())
    } catch (e: FirebaseFirestoreException) {
        when (e.code) {
            FirebaseFirestoreException.Code.DEADLINE_EXCEEDED -> Response.Error(DataErrors.Remote.REQUEST_TIMEOUT)
            FirebaseFirestoreException.Code.NOT_FOUND -> Response.Error(DataErrors.Remote.NOT_FOUND)
            FirebaseFirestoreException.Code.INTERNAL -> Response.Error(DataErrors.Remote.SERVER)
            FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED -> Response.Error(DataErrors.Remote.TOO_MANY_REQUESTS)
            FirebaseFirestoreException.Code.UNAUTHENTICATED -> Response.Error(DataErrors.Remote.UNAUTHORIZED)
            FirebaseFirestoreException.Code.CANCELLED -> Response.Error(DataErrors.Remote.UNKNOWN)
            FirebaseFirestoreException.Code.UNKNOWN -> Response.Error(DataErrors.Remote.UNKNOWN)
            else -> Response.Error(DataErrors.Remote.UNKNOWN)
        }
    } catch (e: Exception) {
        Response.Error(DataErrors.Remote.UNKNOWN)
    }
}

suspend inline fun <T> query(
    action: suspend () -> T
): Response<T, DataErrors.Local> {
    return try {
        Response.Success(action())
    }catch (e: SQLiteDiskIOException){
        Response.Error(DataErrors.Local.DISK_IO_ERROR)
    }catch (e: SQLiteDatabaseCorruptException){
        Response.Error(DataErrors.Local.DATABASE_CORRUPT)
    }catch (e: SQLiteConstraintException){
        Response.Error(DataErrors.Local.QUERY_FAILED)
    }
    catch (e: SQLiteException){
        Response.Error(DataErrors.Local.UNKNOWN)
    }
    catch (e: Exception){
        Response.Error(DataErrors.Local.UNKNOWN)
    }
}