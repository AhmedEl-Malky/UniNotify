package com.malky.uninotify.app.navigation

import com.malky.uninotify.domain.core.EventType
import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object AppGraph : Destination

    @Serializable
    data object Splash : Destination

    @Serializable
    data object AuthenticationGraph : Destination

    @Serializable
    data object Login : Destination

    @Serializable
    data object Signup : Destination

    @Serializable
    data object MainGraph : Destination

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
    data class CategorizedEvents(val eventType: EventType) : Destination




}