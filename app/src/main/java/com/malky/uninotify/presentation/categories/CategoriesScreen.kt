package com.malky.uninotify.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.malky.uninotify.app.navigation.Destination
import com.malky.uninotify.app.navigation.LocalNavController
import com.malky.uninotify.app.theme.CollegeAlertTheme
import com.malky.uninotify.presentation.DeviceConfiguration
import com.malky.uninotify.presentation.composables.CategoryCard
import com.malky.uninotify.presentation.composables.HeaderSection

@Composable
fun CategoriesScreen(
    deviceConfiguration: DeviceConfiguration,
    viewModel: CategoriesViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CategoriesScreenContent(
        state = state,
        deviceConfiguration = deviceConfiguration
    )
}

@Composable
private fun CategoriesScreenContent(
    state: CategoriesState,
    deviceConfiguration: DeviceConfiguration,
    navController: NavHostController = LocalNavController.current
) {
    val widthFraction = remember {
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT,
            DeviceConfiguration.TABLET_PORTRAIT -> 1f

            DeviceConfiguration.MOBILE_LANDSCAPE,
            DeviceConfiguration.FOLDABLE,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.LARGE_TABLET,
            DeviceConfiguration.DESKTOP -> 0.7f
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderSection(
                title = "Categories",
                subtitle = "Browse events by category",
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
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(widthFraction)
                    .fillMaxHeight()
                    .padding(innerPadding)
                    .padding(top = 24.dp)
                    .align(Alignment.CenterHorizontally),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = innerPadding.calculateBottomPadding() + 4.dp
                )
            ) {
                items(items = state.categories) { category ->
                    CategoryCard(
                        eventType = category.first,
                        eventsCount = category.second,
                        onClick = {
                            navController.navigate(Destination.CategorizedEvents(eventType = category.first))
                        }
                    )
                }
            }
        }
    }
}

@Preview(device = "spec:parent=pixel_tablet,orientation=landscape")
@Composable
private fun PreviewCategoriesScreen() {
    CollegeAlertTheme {
        CategoriesScreenContent(
            state = CategoriesState(),
            deviceConfiguration = DeviceConfiguration.TABLET_LANDSCAPE
        )
    }
}