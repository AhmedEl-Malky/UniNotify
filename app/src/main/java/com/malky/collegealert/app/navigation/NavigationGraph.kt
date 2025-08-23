package com.malky.collegealert.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.malky.collegealert.presentation.categories.CategoriesScreen
import com.malky.collegealert.presentation.eventDetails.EventDetailsScreen
import com.malky.collegealert.presentation.home.HomeScreen
import com.malky.collegealert.presentation.savedEvents.SavedEventsScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController found!")
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = Destination.Home) {
            composable<Destination.Home> {
                HomeScreen(viewModel = hiltViewModel())
            }
            composable<Destination.Categories> {
                CategoriesScreen(viewModel = hiltViewModel())
            }
            composable<Destination.SavedEvents> {
                SavedEventsScreen(viewModel = hiltViewModel())
            }
            composable<Destination.EventDetails> {
                EventDetailsScreen(viewModel = hiltViewModel())
            }
        }
    }
}