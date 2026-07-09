package com.malky.uninotify.domain

import java.util.Date

data class Event(
    val id:String,
    val title: String,
    val description: String,
    val type: EventType,
    val date: Date,
    val location: String,
    val isSaved: Boolean
)