package com.malky.uninotify.presentation.savedEvents

import com.malky.uninotify.domain.Event
import com.malky.uninotify.utils.UiText

data class SavedEventsState(
    val error: UiText? = null,
    val savedEvents: List<Event> = emptyList()
)