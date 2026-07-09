package com.malky.uninotify.presentation.categorizedEvents

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.malky.uninotify.app.navigation.Destination
import com.malky.uninotify.domain.Event
import com.malky.uninotify.domain.EventType
import com.malky.uninotify.data.repositories.EventsRepository
import com.malky.uninotify.data.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CategorizedEventsViewModel @Inject constructor(
    private val eventsRepo: EventsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val eventType: EventType = savedStateHandle.toRoute<Destination.CategorizedEvents>().eventType
    val events = mutableStateListOf<Event>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            eventsRepo.selectEventsByType(eventType)
                .onSuccess { result ->
                    events.addAll(result)
                }
        }
    }

}