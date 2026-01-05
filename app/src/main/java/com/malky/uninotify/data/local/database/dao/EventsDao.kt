package com.malky.uninotify.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.malky.uninotify.data.local.database.entities.EventEntity
import com.malky.uninotify.domain.core.EventCategory
import com.malky.uninotify.domain.core.EventType
import java.util.Date

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewEvents(events: List<EventEntity>)

    @Query("""
        UPDATE Events_Table SET
        title = :title,
        description = :description,
        location = :location,
        date = :date,
        type = :type
        WHERE id = :id
    """)
    suspend fun updateEventPreservingSavedState(
        id: String,
        title: String,
        description: String,
        location: String,
        date: Date,
        type: EventType
    )

    @Transaction
    suspend fun cacheEvents(events: List<EventEntity>){
        // 1. Insert all events. New ones will be added, existing ones will be ignored.
        insertNewEvents(events)

        // 2. For each event, issue an update call.
        //    This will update existing events while preserving their 'isSaved' state.
        //    For new events, this query will find no matching 'id' and do nothing.
        events.forEach { event ->
            updateEventPreservingSavedState(
                id = event.id,
                title = event.title,
                description = event.description,
                location = event.location,
                date = event.date,
                type = event.type
            )
        }
    }

    @Query("Update Events_Table SET isSaved = :isSaved WHERE id = :id")
    suspend fun updateEventSaveState(id: String,isSaved: Boolean)

    @Query("SELECT * FROM Events_Table")
    suspend fun selectAllEvents(): List<EventEntity>

    @Query("SELECT * FROM EVENTS_TABLE WHERE id = :id")
    suspend fun selectEventByID(id: String) : EventEntity

    @Query("SELECT * FROM Events_Table WHERE type = :type")
    suspend fun selectEventsByType(type: EventType) : List<EventEntity>

    @Query("SELECT * FROM EVENTS_TABLE WHERE isSaved = TRUE")
    suspend fun selectSavedEvents() : List<EventEntity>

    @Query("SELECT COUNT(*) FROM EVENTS_TABLE WHERE isSaved = TRUE")
    suspend fun selectSavedEventsCount() : Int

    @Query("SELECT type, COUNT(*) As count FROM EVENTS_TABLE Group by type")
    suspend fun selectEventsCountGroupByType() : List<EventCategory>
}