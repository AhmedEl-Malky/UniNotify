package com.malky.uninotify.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.malky.uninotify.data.local.database.converters.EventTypeConverters
import com.malky.uninotify.data.local.database.dao.EventsDao
import com.malky.uninotify.data.local.database.entities.EventEntity


@Database(
    entities = [EventEntity::class],
    version = 1
)
@TypeConverters(
    EventTypeConverters::class
)
abstract class EventsDatabase : RoomDatabase() {
    abstract val eventsDao : EventsDao
}