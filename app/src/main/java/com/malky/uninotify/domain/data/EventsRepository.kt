package com.malky.uninotify.domain.data

import com.malky.uninotify.domain.core.Event
import com.malky.uninotify.utils.RemoteDataErrors
import com.malky.uninotify.utils.Response

interface EventsRepository {
    suspend fun fetchAllEvents() : Response<List<Event>, RemoteDataErrors>
}