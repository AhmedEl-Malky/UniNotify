package com.malky.collegealert.presentation.eventDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Bookmark
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.malky.collegealert.app.navigation.LocalNavController
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.composables.EventDetailsCard
import com.malky.collegealert.presentation.composables.EventInformationCard
import com.malky.collegealert.presentation.composables.HeaderSection
import com.malky.collegealert.presentation.composables.PrimaryButton
import com.malky.collegealert.presentation.composables.SecondaryButton

@Composable
fun EventDetailsScreen(
    viewModel: EventDetailsViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    EventDetailsScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun EventDetailsScreenContent(
    state: EventDetailsState,
    onAction: (EventDetailsAction) -> Unit,
    navController: NavHostController = LocalNavController.current
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                    title = "Event Details",
                    subtitle = null,
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
            item {
                EventInformationCard(
                    title = state.event.title,
                    description = state.event.description
                )
            }
            item {
                EventDetailsCard(
                    date = state.event.date,
                    time = state.event.time,
                    venue = state.event.location
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .padding(horizontal = 16.dp)
                        .dropShadow(
                            shape = RoundedCornerShape(16.dp),
                            shadow = Shadow(
                                radius = 1.2.dp,
                                color = Color.Black.copy(alpha = 0.3f),
                                offset = DpOffset(x = 0.dp, y = 1.75.dp)
                            )
                        ),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(16.dp),
                        imageVector = Lucide.Bookmark,
                        contentDescription = null
                    )
                    Text(
                        text = "Save Event",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            item {
                SecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .padding(horizontal = 16.dp),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(16.dp),
                        imageVector = Lucide.Plus,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Add to Calender",
                        style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewEventDetailsScreen() {
    CollegeAlertTheme {
        EventDetailsScreenContent(
            state = EventDetailsState(),
            onAction = {}
        )
    }
}