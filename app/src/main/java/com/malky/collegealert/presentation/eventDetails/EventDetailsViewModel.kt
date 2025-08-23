package com.malky.collegealert.presentation.eventDetails

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventDetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(EventDetailsState())
    val state = _state.asStateFlow()

    fun onAction(action: EventDetailsAction) {
        when (action) {
            else -> {}
        }
    }

}