package com.malky.uninotify.presentation.savedEvents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.uninotify.domain.data.EventsRepository
import com.malky.uninotify.utils.onError
import com.malky.uninotify.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedEventsViewModel @Inject constructor(
    private val eventsRepo: EventsRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SavedEventsState())
    val state = _state.onStart {
        selectSavedEvents()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _state.value
    )

    private fun selectSavedEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            eventsRepo.selectSavedEvents()
                .onSuccess { events ->
                    _state.update {
                        it.copy(
                            savedEvents = events
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

}