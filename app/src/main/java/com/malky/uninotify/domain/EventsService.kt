package com.malky.uninotify.domain

import com.malky.uninotify.data.remote.dto.EventDTO
import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.utils.RemoteDataErrors
import com.malky.uninotify.utils.Response

interface EventsService {
    suspend fun fetchAllEvents() : Response<EventDTO, RemoteDataErrors>

//    suspend fun retrieveAllEvents() : Response<Event,Local>
}