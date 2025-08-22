package com.malky.collegealert.app.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Home : Destination

    @Serializable
    data class EventDetails(val eventID : Int) : Destination

    @Serializable
    data object Categories : Destination

    @Serializable
    data object Profile : Destination

    @Serializable
    data object SavedEvents : Destination

}