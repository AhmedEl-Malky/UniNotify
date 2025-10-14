package com.malky.uninotify.domain.core

data class Event(
    val title: String,
    val description: String,
    val type: EventType,
    val date: String, // Date
    val time: String,
    val location: String
)