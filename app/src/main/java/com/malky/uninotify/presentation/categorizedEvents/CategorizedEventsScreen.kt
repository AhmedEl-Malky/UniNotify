package com.malky.uninotify.presentation.categorizedEvents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
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
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.malky.uninotify.app.theme.CollegeAlertTheme
import com.malky.uninotify.domain.EventType
import com.malky.uninotify.presentation.DeviceConfiguration
import com.malky.uninotify.presentation.composables.EventCard
import com.malky.uninotify.presentation.composables.HeaderSection

@Composable
fun CategorizedEventsScreen(
    viewModel: CategorizedEventsViewModel,
    deviceConfiguration: DeviceConfiguration
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CategorizedEventsScreenContent(
        state = state,
        eventType = viewModel.eventType,
        onAction = viewModel::onAction,
        deviceConfiguration = deviceConfiguration
    )
}

@Composable
private fun CategorizedEventsScreenContent(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
    state: CategorizedEventsState,
    eventType: EventType,
    onAction: (CategorizedEventsAction) -> Unit,
    deviceConfiguration: DeviceConfiguration,
//    navController: NavHostController = LocalNavController.current
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                LazyColumn(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item {
                        HeaderSection(
                            title = "${eventType}s",
                            subtitle = "2 events available",
                            navigationIcon = {
                                IconButton(
                                    modifier = Modifier.padding(end = 4.dp),
                                    onClick = {
//                                navController.navigateUp()
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
                            }
                        )
                    }
                    items(items = state.categorizedEvents) { event ->
                        EventCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onClick = {},
                            title = event.title,
                            description = event.description,
                            eventType = event.type,
                            date = event.date,
                            time = event.time
                        )
                    }
                }
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                LazyVerticalGrid(
                    modifier = modifier.windowInsetsPadding(WindowInsets.displayCutout),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item(span = { GridItemSpan(2) }) {
                        HeaderSection(
                            title = "${eventType}s",
                            subtitle = "2 events available",
                            navigationIcon = {
                                IconButton(
                                    modifier = Modifier.padding(end = 4.dp),
                                    onClick = {
//                                navController.navigateUp()
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
                            }
                        )
                    }
                    items(items = state.categorizedEvents) { event ->
                        EventCard(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            onClick = {},
                            title = event.title,
                            description = event.description,
                            eventType = event.type,
                            date = event.date,
                            time = event.time
                        )
                    }
                }
            }

            DeviceConfiguration.FOLDABLE,
            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.LARGE_TABLET,
            DeviceConfiguration.DESKTOP -> {
                LazyVerticalGrid(
                    modifier = modifier.windowInsetsPadding(WindowInsets.displayCutout),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item(span = { GridItemSpan(3) }) {
                        HeaderSection(
                            title = "${eventType}s",
                            subtitle = "2 events available",
                            navigationIcon = {
                                IconButton(
                                    modifier = Modifier.padding(end = 4.dp),
                                    onClick = {
//                                navController.navigateUp()
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
                            }
                        )
                    }
                    items(items = state.categorizedEvents) { event ->
                        EventCard(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            onClick = {},
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

@Preview(device = "spec:parent=pixel_tablet,orientation=landscape")
@Composable
private fun PreviewCategorizedEventsScreen() {
    CollegeAlertTheme {
        CategorizedEventsScreenContent(
            state = CategorizedEventsState(),
            eventType = EventType.Seminar,
            onAction = {},
            deviceConfiguration = DeviceConfiguration.TABLET_LANDSCAPE
        )
    }
}