package com.malky.uninotify.presentation.eventDetails

import com.malky.uninotify.domain.Event
import com.malky.uninotify.domain.EventType

data class EventDetailsState(
    val isLoading: Boolean = true,
    val event: Event = Event(
        title = "AI & Machine Learning Seminar",
        description = "Learn about the latest trends in AI and ML from industry experts.",
        type = EventType.Seminar,
        date = "Monday, January 15, 2024",
        time = "10:00 AM",
        location = "Seminar Hall A"
    ),
)