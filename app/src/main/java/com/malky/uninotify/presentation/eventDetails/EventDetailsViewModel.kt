package com.malky.uninotify.presentation.eventDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.malky.uninotify.app.navigation.Destination
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
class EventDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val eventsRepo: EventsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(EventDetailsState())
    val state = _state.onStart {
        selectEventById()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _state.value
    )

    private val evenID = savedStateHandle.toRoute<Destination.EventDetails>().eventId

    fun onAction(action: EventDetailsAction) {
        when (action) {
            EventDetailsAction.OnUpdateEventSaveState -> updateEventSaveState()
        }
    }

    private fun updateEventSaveState(){
        viewModelScope.launch(Dispatchers.IO) {
            val event = _state.value.event
            eventsRepo.updateEvent(event!!.copy(isSaved = !event.isSaved))
        }
    }

    private fun selectEventById() {
        viewModelScope.launch(Dispatchers.IO) {
            eventsRepo.selectEventById(evenID)
                .onSuccess { event ->
                    _state.update {
                        it.copy(
                            error = null,
                            event = event
                        )
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            error = error.toUiText()
                        )
                    }
                }
        }
    }

}