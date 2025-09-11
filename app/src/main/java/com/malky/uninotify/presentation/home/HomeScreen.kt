package com.malky.uninotify.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.icons.lucide.Bell
import com.composables.icons.lucide.BookOpen
import com.composables.icons.lucide.Bookmark
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.User
import com.malky.uninotify.app.navigation.Destination
import com.malky.uninotify.app.navigation.LocalNavController
import com.malky.uninotify.app.theme.CollegeAlertTheme
import com.malky.uninotify.presentation.DeviceConfiguration
import com.malky.uninotify.presentation.composables.EventCard
import com.malky.uninotify.presentation.composables.EventsCountSection
import com.malky.uninotify.presentation.composables.HeaderSection
import com.malky.uninotify.presentation.composables.NavigationCard

@Composable
fun HomeScreen(
    deviceConfiguration: DeviceConfiguration,
    viewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state,
        onAction = viewModel::onAction,
        deviceConfiguration = deviceConfiguration
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    deviceConfiguration: DeviceConfiguration,
    navController: NavHostController = LocalNavController.current
) {
    val navigationList = remember {
        mutableStateMapOf(
            "Categories" to Lucide.BookOpen,
            "Saved" to Lucide.Bookmark,
            "Alerts" to Lucide.Bell,
            "Profile" to Lucide.User
        )
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item {
                        HeaderSection(
                            title = "Welcome",
                            subtitle = "Stay updated with campus events",
                            extraContent = {
                                EventsCountSection(upcomingEventsCount = 4, savedEventsCount = 0)
                            }
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            navigationList.forEach { (key, value) ->
                                NavigationCard(
                                    modifier = Modifier.weight(1f),
                                    icon = value,
                                    label = key,
                                    onClick = {
                                        when (key) {
                                            "Categories" -> {
                                                navController.navigate(Destination.Categories)
                                            }

                                            "Saved" -> {
                                                navController.navigate(Destination.SavedEvents)
                                            }

                                            "Alerts" -> {}
                                            "Profile" -> {
                                                navController.navigate(Destination.Profile)
                                            }

                                            else -> Unit
                                        }
                                    }
                                )
                            }
                        }
                    }
                    item {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .paddingFromBaseline(bottom = 16.dp),
                            text = "Upcoming Events",
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W700)
                        )
                    }
                    items(items = state.eventsList) { event ->
                        EventCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onClick = {
                                navController.navigate(Destination.EventDetails)
                            },
                            title = event.title,
                            description = event.description,
                            eventType = event.type,
                            date = event.date,
                            time = event.time
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
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .consumeWindowInsets(WindowInsets.navigationBars)
                        .windowInsetsPadding(WindowInsets.displayCutout),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .widthIn(max = 128.dp)
                            .fillMaxHeight(if (deviceConfiguration == DeviceConfiguration.MOBILE_LANDSCAPE) 0.9f else 0.7f)
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        navigationList.forEach { (key, value) ->
                            NavigationCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                icon = value,
                                label = key,
                                onClick = {
                                    when (key) {
                                        "Categories" -> {
                                            navController.navigate(Destination.Categories)
                                        }

                                        "Saved" -> {
                                            navController.navigate(Destination.SavedEvents)
                                        }

                                        "Alerts" -> {}
                                        "Profile" -> {
                                            navController.navigate(Destination.Profile)
                                        }

                                        else -> Unit
                                    }
                                }
                            )
                        }
                    }
                    LazyVerticalGrid(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(color = MaterialTheme.colorScheme.background),
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                    ) {
                        item(span = { GridItemSpan(2) }) {
                            HeaderSection(
                                modifier = Modifier.padding(bottom = 12.dp),
                                title = "Welcome",
                                subtitle = "Stay updated with campus events",
                                extraContent = {
                                    EventsCountSection(
                                        upcomingEventsCount = 4,
                                        savedEventsCount = 0
                                    )
                                }
                            )
                        }
                        item(span = { GridItemSpan(2) }) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .paddingFromBaseline(bottom = 16.dp),
                                text = "Upcoming Events",
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W700)
                            )
                        }
                        items(items = state.eventsList) { event ->
                            EventCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(end = 8.dp),
                                onClick = {
                                    navController.navigate(Destination.EventDetails)
                                },
                                title = event.title,
                                description = event.description,
                                eventType = event.type,
                                date = event.date,
                                time = event.time
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = "spec:parent=pixel_9,orientation=portrait")
@Composable
private fun PreviewHomeScreen() {
    CollegeAlertTheme {
        HomeScreenContent(
            state = HomeState(),
            onAction = {},
            deviceConfiguration = DeviceConfiguration.MOBILE_PORTRAIT
        )
    }
}

