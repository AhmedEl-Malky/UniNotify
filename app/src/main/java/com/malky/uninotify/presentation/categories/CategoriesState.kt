package com.malky.uninotify.presentation.categories

import com.malky.uninotify.domain.core.EventType

data class CategoriesState(
    val isLoading: Boolean = true,
    val categories: Map<EventType, Int> = mapOf(
        EventType.Seminar to 10,
        EventType.Exam to 10,
        EventType.Fest to 10,
        EventType.Notice to 10
    )
)