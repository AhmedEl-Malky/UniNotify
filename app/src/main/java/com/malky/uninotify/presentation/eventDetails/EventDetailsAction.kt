package com.malky.uninotify.presentation.eventDetails

sealed interface EventDetailsAction {
    data object OnUpdateEventSaveState : EventDetailsAction
}