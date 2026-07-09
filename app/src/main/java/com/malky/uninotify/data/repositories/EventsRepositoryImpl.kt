package com.malky.uninotify.data.repositories

import com.malky.uninotify.data.local.database.dao.EventsDao
import com.malky.uninotify.data.local.database.entities.EventEntity
import com.malky.uninotify.data.mappers.toEvent
import com.malky.uninotify.data.mappers.toEventEntity
import com.malky.uninotify.domain.Event
import com.malky.uninotify.domain.EventCategory
import com.malky.uninotify.domain.EventType
import com.malky.uninotify.data.remote.EventsService
import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.data.utils.Response
import com.malky.uninotify.data.utils.map
import com.malky.uninotify.data.utils.query

class EventsRepositoryImpl(
    private val service: EventsService,
    private val dao: EventsDao
) : EventsRepository {

    override suspend fun fetchAllEvents(): Response<List<EventEntity>, DataErrors.Remote> {
        return service.fetchAllEvents().map { list ->
            list.map { DTO ->
                DTO.toEventEntity()
            }
        }
    }

    override suspend fun cacheEvents(events: List<EventEntity>): Response<Unit, DataErrors.Local> {
        return query<Unit> {
            dao.cacheEvents(events)
        }
    }

    override suspend fun updateEvent(
        id: String,
        isSaved: Boolean
    ): Response<Unit, DataErrors.Local> {
        return query<Unit> {
            dao.updateEventSaveState(id = id, isSaved = isSaved)
        }
    }

    override suspend fun selectAllEvents(): Response<List<Event>, DataErrors.Local> {
        return query<List<Event>> {
            dao.selectAllEvents().map {
                it.toEvent()
            }
        }
    }

    override suspend fun selectEventById(id: String): Response<Event, DataErrors.Local> {
        return query { dao.selectEventByID(id).toEvent() }
    }

    override suspend fun selectEventsByType(type: EventType): Response<List<Event>, DataErrors.Local> {
        return query<List<Event>> {
            dao.selectEventsByType(type).map { it.toEvent() }
        }
    }

    override suspend fun selectSavedEvents(): Response<List<Event>, DataErrors.Local> {
        return query<List<Event>> { dao.selectSavedEvents().map { it.toEvent() } }
    }

    override suspend fun selectEventsCountGroupByType(): Response<List<EventCategory>, DataErrors.Local> {
        return query{ dao.selectEventsCountGroupByType() }
    }

    override suspend fun selectSavedEventsCount(): Int {
        return dao.selectSavedEventsCount()
    }

}