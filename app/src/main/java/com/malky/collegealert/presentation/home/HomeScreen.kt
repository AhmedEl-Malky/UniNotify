package com.malky.collegealert.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import com.malky.collegealert.app.navigation.Destination
import com.malky.collegealert.app.navigation.LocalNavController
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.composables.EventCard
import com.malky.collegealert.presentation.composables.EventsCountSection
import com.malky.collegealert.presentation.composables.HeaderSection
import com.malky.collegealert.presentation.composables.NavigationItemsRow

@Composable
fun HomeScreen(
    windowSize: WindowSizeClass,
    viewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
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
                    title = "Welcome",
                    subtitle = "Stay updated with campus events",
                    extraContent = {
                        EventsCountSection(upcomingEventsCount = 4, savedEventsCount = 0)
                    }
                )
            }
            item {
                NavigationItemsRow(
                    navController = navController,
                    navigationList = state.navigationList
                )
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
            items(items = state.eventsList){ event ->
                EventCard(
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

@Preview(showSystemUi = true)
@Composable
private fun PreviewHomeScreen() {
    CollegeAlertTheme {
        HomeScreenContent(
            state = HomeState(),
            onAction = {}
        )
    }
}
