package com.malky.uninotify.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.malky.uninotify.data.mappers.toEventDTO
import com.malky.uninotify.data.remote.dto.EventDTO
import com.malky.uninotify.domain.data.EventsService
import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.utils.Response
import com.malky.uninotify.utils.retrieveFromFireStore
import kotlinx.coroutines.tasks.await

class EventsServiceImpl(
    private val dp: FirebaseFirestore
) : EventsService {
    override suspend fun fetchAllEvents(): Response<List<EventDTO>, DataErrors.Remote> {

        return retrieveFromFireStore {
            val result = dp.collection("Events").get(Source.SERVER).await()
            result.documents.map { documentSnapshot ->
                documentSnapshot.toEventDTO()
            }
        }

    }

}