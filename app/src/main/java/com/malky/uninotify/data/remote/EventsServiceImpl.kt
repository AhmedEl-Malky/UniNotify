package com.malky.uninotify.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.malky.uninotify.data.mappers.toEventDTO
import com.malky.uninotify.data.remote.dto.EventDTO
import com.malky.uninotify.domain.data.EventsService
import com.malky.uninotify.utils.RemoteDataErrors
import com.malky.uninotify.utils.Response
import com.malky.uninotify.utils.retrieveFromFireStore
import kotlinx.coroutines.tasks.await

class EventsServiceImpl(
    private val dp: FirebaseFirestore
) : EventsService {
    override suspend fun fetchAllEvents(): Response<List<EventDTO>, RemoteDataErrors> {

        return retrieveFromFireStore {
            val result = dp.collection("Events").get().await()
            result.documents.map { documentSnapshot ->
                documentSnapshot.toEventDTO()
            }
        }

    }

}