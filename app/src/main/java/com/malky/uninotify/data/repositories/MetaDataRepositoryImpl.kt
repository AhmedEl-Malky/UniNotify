package com.malky.uninotify.data.repositories

import com.malky.uninotify.data.local.preferences.MetaDataPreferences
import com.malky.uninotify.domain.data.MetaDataRepository
import com.malky.uninotify.domain.data.MetaDataService
import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.utils.Response

class MetaDataRepositoryImpl(
    private val metaData: MetaDataPreferences,
    private val metaDataService: MetaDataService
) : MetaDataRepository {
    override suspend fun updateVersion(version: String) {
        metaData.updateVersion(version)
    }

    override suspend fun getLocalVersion(): String? {
        return metaData.getVersion()
    }

    override suspend fun getRemoteVersion(): Response<String?, DataErrors.Remote> {
        return metaDataService.getVersion()
    }
}