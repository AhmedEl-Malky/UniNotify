package com.malky.uninotify.presentation.categorizedEvents

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.malky.uninotify.app.navigation.Destination
import com.malky.uninotify.domain.core.EventType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CategorizedEventsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(CategorizedEventsState())
    val state = _state.asStateFlow()

    val eventType: EventType = savedStateHandle.toRoute<Destination.CategorizedEvents>().eventType
    fun onAction(action: CategorizedEventsAction) {
        when (action) {
            else -> {}
        }
    }

}