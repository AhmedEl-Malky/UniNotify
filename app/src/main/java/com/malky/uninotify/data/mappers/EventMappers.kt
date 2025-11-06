package com.malky.uninotify.data.mappers

import com.google.firebase.firestore.DocumentSnapshot
import com.malky.uninotify.data.local.database.entities.EventEntity
import com.malky.uninotify.data.remote.dto.EventDTO
import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.domain.core.EventType

fun DocumentSnapshot.toEventDTO(): EventDTO {
    return EventDTO(
        id = id,
        title = this.getString("title").toString(),
        description = this.getString("description").toString(),
        type = this.getString("type").toString(),
        location = this.getString("location").toString(),
        date = this.getTimestamp("date")!!
    )
}

fun EventDTO.toEventEntity(): EventEntity {
    return EventEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        type = EventType.valueOf(this.type),
        location = this.location,
        date = this.date.toDate(),
        isSaved = false
    )
}

fun EventEntity.toEvent() : Event{
    return Event(
        id = this.id,
        title = this.title,
        description = this.description,
        type = this.type,
        location = this.location,
        date = this.date,
        isSaved = this.isSaved
    )
}

fun Event.toEventEntity() : EventEntity{
    return EventEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        type = this.type,
        location = this.location,
        date = this.date,
        isSaved = this.isSaved
    )
}