package com.malky.uninotify.domain.data

import com.malky.uninotify.data.local.database.entities.EventEntity
import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.domain.core.EventCategory
import com.malky.uninotify.domain.core.EventType
import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.utils.Response

interface EventsRepository {
    suspend fun fetchAllEvents(): Response<List<EventEntity>, DataErrors.Remote>

    suspend fun cacheEvents(events: List<EventEntity>): Response<Unit, DataErrors.Local>

    suspend fun updateEvent(id:String,isSaved:Boolean): Response<Unit, DataErrors.Local>

    suspend fun selectAllEvents(): Response<List<Event>, DataErrors.Local>

    suspend fun selectEventById(id:String) : Response<Event, DataErrors.Local>

    suspend fun selectEventsByType(type: EventType): Response<List<Event>, DataErrors.Local>

    suspend fun selectSavedEvents() : Response<List<Event>, DataErrors.Local>

    suspend fun selectEventsCountGroupByType() : Response<List<EventCategory>, DataErrors.Local>

    suspend fun selectSavedEventsCount() : Int
}