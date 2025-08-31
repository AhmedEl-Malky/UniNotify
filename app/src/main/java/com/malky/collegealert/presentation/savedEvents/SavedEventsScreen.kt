package com.malky.collegealert.presentation.savedEvents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.malky.collegealert.app.navigation.LocalNavController
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.DeviceConfiguration
import com.malky.collegealert.presentation.composables.EventCard
import com.malky.collegealert.presentation.composables.HeaderSection

@Composable
fun SavedEventsScreen(
    deviceConfiguration: DeviceConfiguration,
    viewModel: SavedEventsViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SavedEventsScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun SavedEventsScreenContent(
    state: SavedEventsState,
    onAction: (SavedEventsAction) -> Unit,
    navController: NavHostController = LocalNavController.current
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .consumeWindowInsets(WindowInsets.navigationBars),
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
                )
            }
            items(items = state.savedEvents) { event ->
                EventCard(
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

@Preview
@Composable
private fun PreviewSavedEventsScreen() {
    CollegeAlertTheme {
        SavedEventsScreenContent(
            state = SavedEventsState(),
            onAction = {}
        )
    }
}