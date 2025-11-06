package com.malky.uninotify.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.malky.uninotify.data.local.database.entities.EventEntity
import com.malky.uninotify.domain.core.EventType

@Dao
interface EventsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun cacheEvents(events: List<EventEntity>)

    @Update
    suspend fun updateEvent(event: EventEntity)

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

    @Query("SELECT COUNT(*) FROM EVENTS_TABLE WHERE type = :type")
    suspend fun selectEventsCountByType(type: EventType) : Int
}