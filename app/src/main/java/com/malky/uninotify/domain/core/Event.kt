package com.malky.uninotify.domain.core

import java.util.Date

data class Event(
    val title: String,
    val description: String,
    val type: EventType,
    val date: Date,
    val location: String
)