package com.malky.uninotify.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class MetaDataPreferences (private val context : Context) {
    val Context.metaDataPreferences: DataStore<Preferences> by preferencesDataStore(name = "meta-data")

    private val versionPreference = stringPreferencesKey(VERSION_KEY)

    suspend fun updateVersion(version: String){
        context.metaDataPreferences.edit { metaData ->
            metaData[versionPreference] = version
        }
    }

    suspend fun getVersion() : String? {
        return context.metaDataPreferences.data.map { metaData ->
            metaData[versionPreference]
        }.firstOrNull()
    }

    companion object {
        const val VERSION_KEY = "VERSION"
    }
}