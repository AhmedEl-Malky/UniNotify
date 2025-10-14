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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.presentation.DeviceConfiguration
import com.malky.uninotify.presentation.authentication.login.LoginScreen
import com.malky.uninotify.presentation.authentication.signup.SignupScreen
import com.malky.uninotify.presentation.categories.CategoriesScreen
import com.malky.uninotify.presentation.categorizedEvents.CategorizedEventsScreen
import com.malky.uninotify.presentation.eventDetails.EventDetailsScreen
import com.malky.uninotify.presentation.home.HomeScreen
import com.malky.uninotify.presentation.profile.ProfileScreen
import com.malky.uninotify.presentation.savedEvents.SavedEventsScreen
import javax.inject.Inject

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController found!")
}

val LocalUser = compositionLocalOf<FirebaseUser?> {
    error("No current user")
}

val auth = FirebaseAuth.getInstance()


@Composable
fun NavigationGraph(
    deviceConfiguration: DeviceConfiguration
) {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalUser provides auth.currentUser
    ) {
        val user = LocalUser.current
        NavHost(navController = navController, startDestination = Destination.AppGraph) {
            navigation<Destination.AppGraph>(
                startDestination = if (user != null)
                    Destination.MainGraph
                else
                    Destination.AuthenticationGraph
            ) {
                navigation<Destination.AuthenticationGraph>(startDestination = Destination.Login) {
                    composable<Destination.Login> {
                        LoginScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.Signup> {
                        SignupScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel()
                        )
                    }
                }
                navigation<Destination.MainGraph>(startDestination = Destination.Home) {
                    composable<Destination.Home> {
                        HomeScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.Categories> {
                        CategoriesScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.SavedEvents> {
                        SavedEventsScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.EventDetails> {
                        EventDetailsScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.Profile> {
                        ProfileScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel()
                        )
                    }
                    composable<Destination.CategorizedEvents> {
                        CategorizedEventsScreen(
                            deviceConfiguration = deviceConfiguration,
                            viewModel = hiltViewModel()
                        )
                    }
                }
            }
        }
    }
}