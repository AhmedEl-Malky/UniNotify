package com.malky.uninotify.data.remote.dto

import com.google.firebase.Timestamp


data class EventDTO(
    val title: String,
    val type:String,
    val description: String,
    val location: String,
    val date: Timestamp
)