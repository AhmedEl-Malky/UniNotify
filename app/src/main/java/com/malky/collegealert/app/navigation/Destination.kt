package com.malky.collegealert.app.navigation

import com.malky.collegealert.domain.EventType
import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Home : Destination

    @Serializable
    data object EventDetails : Destination

    @Serializable
    data object Categories : Destination

    @Serializable
    data object Profile : Destination

    @Serializable
    data object SavedEvents : Destination

    @Serializable
    data class CategorizedEvents(val eventType: EventType)

}