package com.malky.uninotify.domain.core

import com.malky.uninotify.domain.core.EventType

data class Event(
    val title: String,
    val description: String,
    val type: EventType,
    val date: String,
    val time: String,
    val location: String
)