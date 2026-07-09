package com.malky.uninotify.data.repositories

import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.data.utils.Response

interface MetaDataRepository {
    suspend fun updateVersion(version: String)
    suspend fun getLocalVersion(): String?

    suspend fun getRemoteVersion(): Response<String?, DataErrors.Remote>
}