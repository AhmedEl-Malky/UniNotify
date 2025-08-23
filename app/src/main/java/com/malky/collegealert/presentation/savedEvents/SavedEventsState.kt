package com.malky.collegealert.presentation.savedEvents

import com.malky.collegealert.domain.Event
import com.malky.collegealert.domain.EventType

data class SavedEventsState(
    val isLoading: Boolean = true,
    val savedEvents: List<Event> = listOf(
        Event(
            title = "AI & Machine Learning Seminar",
            description = "Learn about the latest trends in AI and ML from industry experts.",
            type = EventType.Seminar,
            date = "1/15/2024",
            time = "10:00 AM",
            location = "Online"
        ),
        Event(
            title = "Library Closure Notice",
            description = "Library will be closed for maintenance from Jan 18-19.",
            type = EventType.Notice,
            date = "1/15/2024",
            time = "10:00 AM",
            location = "Online"
        )
    )
)