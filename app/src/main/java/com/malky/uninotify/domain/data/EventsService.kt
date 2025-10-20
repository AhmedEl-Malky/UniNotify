package com.malky.uninotify.domain.data

import com.malky.uninotify.data.remote.dto.EventDTO
import com.malky.uninotify.utils.RemoteDataErrors
import com.malky.uninotify.utils.Response

interface EventsService {
    suspend fun fetchAllEvents() : Response<List<EventDTO>, RemoteDataErrors>

//    suspend fun retrieveAllEvents() : Response<Event,Local>
}