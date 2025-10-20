package com.malky.uninotify.presentation.savedEvents

import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.domain.core.EventType
import java.util.Date

data class SavedEventsState(
    val isLoading: Boolean = true,
    val savedEvents: List<Event> = listOf(
        Event(
            title = "AI & Machine Learning Seminar",
            description = "Learn about the latest trends in AI and ML from industry experts.",
            type = EventType.Seminar,
            date = Date(),
            location = "Online"
        ),
        Event(
            title = "Library Closure Notice",
            description = "Library will be closed for maintenance from Jan 18-19.",
            type = EventType.Notice,
            date = Date(),
            location = "Online"
        ),
        Event(
            title = "AI & Machine Learning Seminar",
            description = "Learn about the latest trends in AI and ML from industry experts.",
            type = EventType.Seminar,
            date = Date(),
            location = "Online"
        ),
        Event(
            title = "Library Closure Notice",
            description = "Library will be closed for maintenance from Jan 18-19.",
            type = EventType.Notice,
            date = Date(),
            location = "Online"
        ),
    )
)