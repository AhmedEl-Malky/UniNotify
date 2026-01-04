package com.malky.uninotify.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.uninotify.data.local.database.entities.EventEntity
import com.malky.uninotify.domain.data.EventsRepository
import com.malky.uninotify.domain.data.MetaDataRepository
import com.malky.uninotify.utils.onError
import com.malky.uninotify.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val eventsRepo: EventsRepository,
    private val metaDataRepo: MetaDataRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.onStart {
        compareVersions()
        selectAllEvents()
        selectSavedEventsCount()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = _state.value
    )


    private suspend fun getLocalVersion(): String? {
        return metaDataRepo.getLocalVersion()
    }

    private fun updateLocalVersion(version: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            metaDataRepo.updateVersion(version ?: "1.0")
        }
    }

    private suspend fun fetchRemoteVersion(): String? {
        var result: String? = null
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        metaDataRepo.getRemoteVersion()
            .onSuccess { version ->
                result = version
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
            .onError { error ->
                result = null
                _state.update {
                    it.copy(
                        error = error.toUiText()
                    )
                }
            }
        return result
    }


    private suspend fun compareVersions() {
        val localVersion = getLocalVersion()
        val remoteVersion = fetchRemoteVersion()
        if (remoteVersion != localVersion) {
            updateLocalVersion(remoteVersion)
            fetchRemoteEvents()
        }
    }

    private suspend fun fetchRemoteEvents() {
        _state.update {
            it.copy(
                isLoading = true,
                error = null
            )
        }
        eventsRepo.fetchAllEvents()
            .onSuccess { events ->
                cacheRemoteEvents(events)
                _state.update {
                    it.copy(
                        isLoading = false,
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = error.toUiText()
                    )
                }
            }
        delay(500)
        _state.update {
            it.copy(
                error = null
            )
        }
    }

    fun selectSavedEventsCount() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    savedEventsCount = eventsRepo.selectSavedEventsCount()
                )
            }
        }
    }

    private fun cacheRemoteEvents(events: List<EventEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            eventsRepo.cacheEvents(events).onError { error ->
                _state.update {
                    it.copy(
                        error = error.toUiText()
                    )
                }
            }

        }
    }


    private suspend fun selectAllEvents() {
        eventsRepo.selectAllEvents()
            .onSuccess { events ->
                _state.update {
                    it.copy(
                        events = events
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        error = error.toUiText()
                    )
                }
            }
    }

}