package com.malky.uninotify.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.malky.uninotify.presentation.utils.DeviceConfiguration
import com.malky.uninotify.presentation.UserSharedViewModel
import com.malky.uninotify.presentation.authentication.login.LoginScreen
import com.malky.uninotify.presentation.authentication.signup.SignupScreen
import com.malky.uninotify.presentation.categories.CategoriesScreen
import com.malky.uninotify.presentation.categorizedEvents.CategorizedEventsScreen
import com.malky.uninotify.presentation.eventDetails.EventDetailsScreen
import com.malky.uninotify.presentation.home.HomeScreen
import com.malky.uninotify.presentation.profile.ProfileScreen
import com.malky.uninotify.presentation.savedEvents.SavedEventsScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController found!")
}

@Composable
fun NavigationGraph(
    deviceConfiguration: DeviceConfiguration,
) {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController,
    ) {
        val userViewModel: UserSharedViewModel = hiltViewModel()
        NavHost(navController = navController, startDestination = Destination.AppGraph) {
            navigation<Destination.AppGraph>(
                startDestination = if (userViewModel.user != null)
                    Destination.MainGraph
                else
                    Destination.AuthenticationGraph
            ) {
                navigation<Destination.AuthenticationGraph>(startDestination = Destination.Login) {
                    composable<Destination.Login> {
                        LoginScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel(),
                            updateUser = userViewModel::updateUser
                        )
                    }
                    composable<Destination.Signup> {
                        SignupScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel(),
                            updateUser = userViewModel::updateUser
                        )
                    }
                }
                navigation<Destination.MainGraph>(startDestination = Destination.Home) {
                    composable<Destination.Home> {
                        HomeScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel(),
                            user = userViewModel.user!!
                        )
                    }
                    composable<Destination.Categories> {
                        CategoriesScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel(),
                            user = userViewModel.user!!
                        )
                    }
                    composable<Destination.SavedEvents> {
                        SavedEventsScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel(),
                            user = userViewModel.user!!
                        )
                    }
                    composable<Destination.EventDetails> {
                        EventDetailsScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel(),
                            user = userViewModel.user!!
                        )
                    }
                    composable<Destination.Profile> {
                        ProfileScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel(),
                            updateUser = userViewModel::updateUser,
                            user = userViewModel.user
                        )
                    }
                    composable<Destination.CategorizedEvents> {
                        CategorizedEventsScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel(),
                            user = userViewModel.user!!
                        )
                    }
                }
            }
        }
    }
}