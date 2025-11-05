package com.malky.uninotify.data.local.database.converters

import androidx.room.TypeConverter
import com.malky.uninotify.domain.core.EventType
import java.util.Date

class EventTypeConverters {
    @TypeConverter
    fun dateToLong(date: Date) : Long = date.time

    @TypeConverter
    fun longToDate(time: Long) : Date = Date(time)

    @TypeConverter
    fun eventTypeToString(type: EventType) : String = type.name

    @TypeConverter
    fun stringToEventType(type: String): EventType = EventType.valueOf(type)

}