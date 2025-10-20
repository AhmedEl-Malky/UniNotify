package com.malky.uninotify.data.mappers

import com.google.firebase.firestore.DocumentSnapshot
import com.malky.uninotify.data.remote.dto.EventDTO
import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.domain.core.EventType

fun DocumentSnapshot.toEventDTO(): EventDTO {
    return EventDTO(
        title = this.getString("title").toString(),
        description = this.getString("description").toString(),
        type = this.getString("type").toString(),
        location = this.getString("location").toString(),
        date = this.getTimestamp("date")!!
    )
}

fun EventDTO.toEvent(): Event {
    val type = when (this.type) {
        "Seminar" -> EventType.Seminar
        "Fest" -> EventType.Fest
        "Exam" -> EventType.Exam
        else -> EventType.Notice
    }
    return Event(
    title = this.title,
    description = this.description,
    type = type,
    location = this.location,
    date = this.date.toDate(),
    )
}