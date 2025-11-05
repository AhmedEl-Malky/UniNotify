package com.malky.uninotify.domain.data

import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.utils.Response

interface MetaDataService {
    suspend fun getVersion(): Response<String?, DataErrors.Remote>
}