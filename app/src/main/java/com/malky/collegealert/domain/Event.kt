package com.malky.collegealert.domain

import java.sql.Time
import java.util.Date

data class Event(
    val title: String,
    val description: String,
    val type: EventType,
    val date: String,
    val time: String,
    val location: String
)