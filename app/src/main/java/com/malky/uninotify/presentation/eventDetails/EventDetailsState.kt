package com.malky.uninotify.presentation.eventDetails

import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.domain.core.EventType
import java.util.Date

data class EventDetailsState(
    val isLoading: Boolean = true,
    val event: Event = Event(
        title = "AI & Machine Learning Seminar",
        description = "Learn about the latest trends in AI and ML from industry experts.",
        type = EventType.Seminar,
        date = Date(),
        location = "Seminar Hall A",
        isSaved = false
    ),
)