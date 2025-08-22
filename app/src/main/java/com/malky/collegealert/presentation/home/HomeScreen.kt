package com.malky.collegealert.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.User
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.composables.EventCard
import com.malky.collegealert.presentation.composables.EventsCountSection
import com.malky.collegealert.presentation.composables.HeaderSection
import com.malky.collegealert.presentation.composables.NavigationItemsRow

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    HomeScreenContent(
        state = state,
        onAction = onAction
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)

        ) {
            item {
                HeaderSection(
                    title = "Welcome",
                    subtitle = "Stay updated with campus events",
                    profileButton = {
                        IconButton(
                            onClick = {},
                        ) {
                            Icon(
                                imageVector = Lucide.User,
                                contentDescription = "Go to Profile",
                                tint = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    },
                    extraContent = {
                        EventsCountSection(upcomingEventsCount = 4, savedEventsCount = 0)
                    }
                )
            }
            item { NavigationItemsRow() }
            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .paddingFromBaseline(bottom = 16.dp),
                    text = "Upcoming Events",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W700)
                )
            }
            items(items = state.eventsList){ event ->
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

@Preview(showSystemUi = true)
@Composable
private fun PreviewHomeScreen() {
    CollegeAlertTheme {
        HomeScreen(
            state = HomeState(),
            onAction = {}
        )
    }
}
