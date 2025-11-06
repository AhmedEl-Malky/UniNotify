package com.malky.uninotify.presentation.savedEvents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.app.navigation.LocalNavController
import com.malky.uninotify.app.theme.UniNotifyTheme
import com.malky.uninotify.presentation.DeviceConfiguration
import com.malky.uninotify.presentation.composables.EventCard
import com.malky.uninotify.presentation.composables.HeaderSection

@Composable
fun SavedEventsScreen(
    viewModel: SavedEventsViewModel,
    deviceConfiguration: DeviceConfiguration,
    user: FirebaseUser
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SavedEventsScreenContent(
        state = state,
        deviceConfiguration = deviceConfiguration,
        user = user
    )
}

@Composable
private fun SavedEventsScreenContent(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
        .consumeWindowInsets(WindowInsets.navigationBars),
    state: SavedEventsState,
    deviceConfiguration: DeviceConfiguration,
    navController: NavHostController = LocalNavController.current,
    user: FirebaseUser
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                LazyColumn(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item {
                        HeaderSection(
                            modifier = Modifier.padding(bottom = 8.dp),
                            title = "Saved Events",
                            subtitle = "${state.savedEvents.size} saved events",
                            navigationIcon = {
                                IconButton(
                                    modifier = Modifier.padding(end = 4.dp),
                                    onClick = {
                                        navController.navigateUp()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(20.dp),
                                        imageVector = Lucide.ArrowLeft,
                                        contentDescription = "Navigate Back",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            },
                            profilePic = user.photoUrl.toString()
                        )
                    }
                    items(items = state.savedEvents) { event ->
                        EventCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onClick = {},
                            title = event.title,
                            description = event.description,
                            eventType = event.type,
                            date = event.date.month.toString(),
                            time = event.date.time.toString()
                        )
                    }
                }
            }

            DeviceConfiguration.MOBILE_LANDSCAPE,
            DeviceConfiguration.FOLDABLE,
            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.LARGE_TABLET,
            DeviceConfiguration.DESKTOP -> {
                LazyVerticalGrid(
                    modifier = modifier.windowInsetsPadding(WindowInsets.displayCutout),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item(span = { GridItemSpan(2) }) {
                        HeaderSection(
                            modifier = Modifier.padding(bottom = 8.dp),
                            title = "Saved Events",
                            subtitle = "${state.savedEvents.size} saved events",
                            navigationIcon = {
                                IconButton(
                                    modifier = Modifier.padding(end = 4.dp),
                                    onClick = {
                                        navController.navigateUp()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(20.dp),
                                        imageVector = Lucide.ArrowLeft,
                                        contentDescription = "Navigate Back",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            },
                            profilePic = user.photoUrl.toString()
                        )
                    }
                    items(items = state.savedEvents) { event ->
                        EventCard(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            onClick = {},
                            title = event.title,
                            description = event.description,
                            eventType = event.type,
                            date = event.date.month.toString(),
                            time = event.date.time.toString()
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = "spec:parent=pixel_tablet,orientation=portrait")
@Composable
private fun PreviewSavedEventsScreen() {
//    UniNotifyTheme {
//        SavedEventsScreenContent(
//            state = SavedEventsState(),
//            onAction = {},
//            deviceConfiguration = DeviceConfiguration.TABLET_PORTRAIT
//        )
//    }
}