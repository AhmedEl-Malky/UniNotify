package com.malky.uninotify.presentation.home

sealed interface HomeAction {
    data object OnGetAllEvents : HomeAction
}