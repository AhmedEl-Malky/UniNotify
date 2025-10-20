package com.malky.uninotify.presentation.eventDetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(EventDetailsState())
    val state = _state.asStateFlow()

    fun onAction(action: EventDetailsAction) {
        when (action) {
            else -> {}
        }
    }

}