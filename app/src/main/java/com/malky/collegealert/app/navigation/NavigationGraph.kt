package com.malky.collegealert.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.malky.collegealert.presentation.authentication.LoginScreen
import com.malky.collegealert.presentation.categories.CategoriesScreen
import com.malky.collegealert.presentation.categorizedEvents.CategorizedEventsScreen
import com.malky.collegealert.presentation.eventDetails.EventDetailsScreen
import com.malky.collegealert.presentation.home.HomeScreen
import com.malky.collegealert.presentation.profile.ProfileScreen
import com.malky.collegealert.presentation.savedEvents.SavedEventsScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController found!")
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = Destination.AppGraph) {
            navigation<Destination.AppGraph>(startDestination = Destination.AuthenticationGraph) {
                navigation<Destination.AuthenticationGraph>(startDestination = Destination.Login) {
                    composable<Destination.Login> {
                        LoginScreen(viewModel = hiltViewModel())
                    }
                }
                navigation<Destination.MainGraph>(startDestination = Destination.Home) {
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
                    composable<Destination.Profile> {
                        ProfileScreen(viewModel = hiltViewModel())
                    }
                    composable<Destination.CategorizedEvents> {
                        CategorizedEventsScreen(viewModel = hiltViewModel())
                    }
                }
            }
        }
    }
}