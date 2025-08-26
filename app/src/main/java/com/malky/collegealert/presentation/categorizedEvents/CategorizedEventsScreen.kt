package com.malky.collegealert.presentation.categorizedEvents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.malky.collegealert.domain.EventType
import com.malky.collegealert.presentation.composables.EventCard
import com.malky.collegealert.presentation.composables.HeaderSection

@Composable
fun CategorizedEventsScreen(
    viewModel: CategorizedEventsViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CategorizedEventsScreenContent(
        state = state,
        eventType = viewModel.eventType,
        onAction = viewModel::onAction
    )
}

@Composable
private fun CategorizedEventsScreenContent(
    state: CategorizedEventsState,
    eventType: EventType,
    onAction: (CategorizedEventsAction) -> Unit,
    navController: NavHostController = LocalNavController.current
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
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
                    }
                )
            }
            items(items = state.categorizedEvents) { event ->
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
private fun PreviewCategorizedEventsScreen() {
    CollegeAlertTheme {
        CategorizedEventsScreenContent(
            state = CategorizedEventsState(),
            eventType = EventType.Seminar,
            onAction = {}
        )
    }
}