package com.malky.uninotify.data.repositories

import com.malky.uninotify.data.local.database.dao.EventsDao
import com.malky.uninotify.data.local.database.entities.EventEntity
import com.malky.uninotify.data.mappers.toEvent
import com.malky.uninotify.data.mappers.toEventEntity
import com.malky.uninotify.data.remote.dto.EventDTO
import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.domain.core.EventType
import com.malky.uninotify.domain.data.EventsRepository
import com.malky.uninotify.domain.data.EventsService
import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.utils.Response
import com.malky.uninotify.utils.map
import com.malky.uninotify.utils.onError
import com.malky.uninotify.utils.onSuccess
import com.malky.uninotify.utils.query

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

    override suspend fun updateEvent(event: EventEntity): Response<Unit, DataErrors.Local> {
        return query<Unit> {
            dao.updateEvent(event)
        }
    }

    override suspend fun selectAllEvents(): Response<List<Event>, DataErrors.Local> {
        return query<List<Event>> {
            dao.selectAllEvents().map {
                it.toEvent()
            }
        }
    }

    override suspend fun selectEventsByType(type: EventType): Response<List<Event>, DataErrors.Local> {
        return query<List<Event>> {
            dao.selectEventsByType(type).map { it.toEvent() }
        }
    }

}