package com.malky.uninotify.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.malky.uninotify.domain.core.EventType
import java.util.Date

@Entity(tableName = "Events_Table")
data class EventEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val title: String,
    val description: String,
    val location: String,
    val date: Date,
    val type: EventType,
    val isSaved: Boolean
)