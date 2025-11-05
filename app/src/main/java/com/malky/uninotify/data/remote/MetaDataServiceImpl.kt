package com.malky.uninotify.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.malky.uninotify.domain.data.MetaDataService
import com.malky.uninotify.utils.DataErrors
import com.malky.uninotify.utils.Response
import com.malky.uninotify.utils.retrieveFromFireStore
import kotlinx.coroutines.tasks.await

class MetaDataServiceImpl(
    private val dp: FirebaseFirestore
) : MetaDataService {
    override suspend fun getVersion(): Response<String?, DataErrors.Remote> {
        return retrieveFromFireStore {
            dp.collection("Meta-Data")
                .document("version")
                .get()
                .await()
                .getString("version")
        }
    }
}