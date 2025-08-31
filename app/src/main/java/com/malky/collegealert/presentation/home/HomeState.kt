package com.malky.collegealert.presentation.home

import com.malky.collegealert.domain.Event
import com.malky.collegealert.domain.EventType

data class HomeState(
    val isLoading: Boolean = true,
    val eventsList:List<Event> = listOf(
        Event(
            title = "AI & Machine Learning Seminar",
            description = "Learn about the latest trends in AI and ML from industry experts.",
            type = EventType.Seminar,
            date = "1/15/2024",
            time = "10:00 AM",
            location = "Online"
        ),
        Event(
            title = "Mid-term Examinations",
            description = "Mid-term exams for all undergraduate courses, Be ready for exams :)",
            type = EventType.Exam,
            date = "1/15/2024",
            time = "10:00 AM",
            location = "Online"
        ),
        Event(
            title = "Annual Tech Fest 2024",
            description = "Join us for the biggest tech festival of the year with competitions and workshops.",
            type = EventType.Fest,
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