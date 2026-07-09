package com.malky.uninotify.presentation.home

import com.malky.uninotify.domain.Event
import com.malky.uninotify.utils.UiText

data class HomeState(
    val isLoading: Boolean = true,
    val error: UiText? = null,
    val events:List<Event> = emptyList(),
    val savedEventsCount: Int = 0
)