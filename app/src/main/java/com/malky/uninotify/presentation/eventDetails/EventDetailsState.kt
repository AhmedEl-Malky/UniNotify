package com.malky.uninotify.presentation.eventDetails

import com.malky.uninotify.domain.Event
import com.malky.uninotify.utils.UiText


data class EventDetailsState(
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val event: Event? = null,

)