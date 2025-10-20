package com.malky.uninotify.data.repositories

import com.malky.uninotify.data.mappers.toEvent
import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.domain.data.EventsRepository
import com.malky.uninotify.domain.data.EventsService
import com.malky.uninotify.utils.RemoteDataErrors
import com.malky.uninotify.utils.Response
import com.malky.uninotify.utils.map

class EventsRepositoryImpl(
    private val service: EventsService
) : EventsRepository {
    override suspend fun fetchAllEvents(): Response<List<Event>, RemoteDataErrors> {
        return service.fetchAllEvents().map { list ->
            list.map { DTO ->
                DTO.toEvent()
            }
        }
    }

}