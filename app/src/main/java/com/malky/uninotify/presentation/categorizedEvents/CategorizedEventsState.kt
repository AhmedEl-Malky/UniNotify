package com.malky.uninotify.presentation.categorizedEvents

import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.domain.core.EventType
import java.util.Date

data class CategorizedEventsState(
    val isLoading: Boolean = true,
    val categorizedEvents: List<Event> = listOf(
        Event(
            title = "AI & Machine Learning Seminar",
            description = "Learn about the latest trends in AI and ML from industry experts.",
            type = EventType.Seminar,
            date = Date(),
            location = "Online",
            isSaved = false
        ),
        Event(
            title = "Mid-term Examinations",
            description = "Mid-term exams for all undergraduate courses.",
            type = EventType.Exam,
            date = Date(),
            location = "Online",
            isSaved = false

        ),
        Event(
            title = "Annual Tech Fest 2024",
            description = "Join us for the biggest tech festival of the year with competitions and workshops.",
            type = EventType.Fest,
            date = Date(),
            location = "Online",
            isSaved = false

        ),
        Event(
            title = "Library Closure Notice",
            description = "Library will be closed for maintenance from Jan 18-19.",
            type = EventType.Notice,
            date = Date(),
            location = "Online",
            isSaved = false

        ),
        Event(
            title = "AI & Machine Learning Seminar",
            description = "Learn about the latest trends in AI and ML from industry experts.",
            type = EventType.Seminar,
            date = Date(),
            location = "Online",
            isSaved = false

        ),
        Event(
            title = "Mid-term Examinations",
            description = "Mid-term exams for all undergraduate courses.",
            type = EventType.Exam,
            date = Date(),
            location = "Online",
            isSaved = false

        ),
        Event(
            title = "Annual Tech Fest 2024",
            description = "Join us for the biggest tech festival of the year with competitions and workshops.",
            type = EventType.Fest,
            date = Date(),
            location = "Online",
            isSaved = false

        ),
        Event(
            title = "Library Closure Notice",
            description = "Library will be closed for maintenance from Jan 18-19.",
            type = EventType.Notice,
            date = Date(),
            location = "Online",
            isSaved = false

        ),
        Event(
            title = "AI & Machine Learning Seminar",
            description = "Learn about the latest trends in AI and ML from industry experts.",
            type = EventType.Seminar,
            date = Date(),
            location = "Online",
            isSaved = false

        ),
    ).filter { it.type == EventType.Seminar },
)