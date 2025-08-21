package com.malky.collegealert.domain

import java.sql.Time
import java.util.Date

data class Event(
    val type: String,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val location: String
)