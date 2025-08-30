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
import androidx.window.core.layout.WindowSizeClass
import com.malky.collegealert.presentation.authentication.LoginScreen
import com.malky.collegealert.presentation.authentication.SignupScreen
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
fun NavigationGraph(
    windowSize: WindowSizeClass
) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = Destination.AppGraph) {
            navigation<Destination.AppGraph>(startDestination = Destination.AuthenticationGraph) {
                navigation<Destination.AuthenticationGraph>(startDestination = Destination.Login) {
                    composable<Destination.Login> {
                        LoginScreen(
                            windowSize = windowSize,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.Signup> {
                        SignupScreen(
                            windowSize = windowSize,
                            viewModel = hiltViewModel()
                        )
                    }
                }
                navigation<Destination.MainGraph>(startDestination = Destination.Home) {
                    composable<Destination.Home> {
                        HomeScreen(
                            windowSize = windowSize,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.Categories> {
                        CategoriesScreen(
                            windowSize = windowSize,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.SavedEvents> {
                        SavedEventsScreen(
                            windowSize = windowSize,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.EventDetails> {
                        EventDetailsScreen(
                            windowSize = windowSize,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.Profile> {
                        ProfileScreen(
                            windowSize = windowSize,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.CategorizedEvents> {
                        CategorizedEventsScreen(
                            windowSize = windowSize,
                            viewModel = hiltViewModel()
                        )
                    }
                }
            }
        }
    }
}