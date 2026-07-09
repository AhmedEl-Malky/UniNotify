package com.malky.uninotify.data.remote

import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.data.utils.Response

interface MetaDataService {
    suspend fun getVersion(): Response<String?, DataErrors.Remote>
}