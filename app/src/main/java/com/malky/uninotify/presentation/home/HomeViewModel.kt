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
        val remoteVersion = getRemoteVersion()
        if (remoteVersion != getLocalVersion()) {
            updateLocalVersion(remoteVersion)
            fetchRemoteEvents()
        }
        selectAllEvents()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = _state.value
    )


    private fun getLocalVersion(): String? {
        var version: String? = null
        viewModelScope.launch(Dispatchers.IO) {
            version = metaDataRepo.getLocalVersion()
        }
        return version
    }

    private fun updateLocalVersion(version: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            metaDataRepo.updateVersion(version ?: "1.0")
        }
    }

    private fun getRemoteVersion(): String? {
        var version: String? = null
        viewModelScope.launch(Dispatchers.IO) {
            metaDataRepo.getRemoteVersion()
                .onSuccess {
                    version = it
                }
                .onError { error ->
                    version = null
                    _state.update {
                        it.copy(
                            error = error.toUiText()
                        )
                    }
                }
        }
        return version
    }

    private fun fetchRemoteEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            eventsRepo.fetchAllEvents()
                .onSuccess { events ->
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
            delay(500)
            _state.update {
                it.copy(
                    error = null
                )
            }
        }
    }


    private fun selectAllEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            eventsRepo.selectAllEvents()
                .onSuccess { events ->
                    _state.update {
                        it.copy(
                            events = events
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            error = it.error
                        )
                    }
                }
        }
    }

}