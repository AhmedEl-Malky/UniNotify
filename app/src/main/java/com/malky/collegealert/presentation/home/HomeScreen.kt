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
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.User
import com.malky.collegealert.app.navigation.LocalNavController
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.composables.EventCard
import com.malky.collegealert.presentation.composables.EventsCountSection
import com.malky.collegealert.presentation.composables.HeaderSection
import com.malky.collegealert.presentation.composables.NavigationItemsRow

@Composable
fun HomeScreen(
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
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .consumeWindowInsets(WindowInsets.navigationBars)
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
            item {
                NavigationItemsRow(navigationList = state.navigationList)
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
        HomeScreenContent(
            state = HomeState(),
            onAction = {}
        )
    }
}
