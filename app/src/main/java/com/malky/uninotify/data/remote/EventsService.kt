package com.malky.uninotify.data.remote

import com.malky.uninotify.data.remote.dto.EventDTO
import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.data.utils.Response

interface EventsService {
    suspend fun fetchAllEvents() : Response<List<EventDTO>, DataErrors.Remote>

//    suspend fun retrieveAllEvents() : Response<Event,Local>
}