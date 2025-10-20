package com.malky.uninotify.presentation.eventDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Bookmark
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.google.firebase.auth.FirebaseUser
import com.malky.uninotify.app.navigation.LocalNavController
import com.malky.uninotify.app.theme.UniNotifyTheme
import com.malky.uninotify.presentation.DeviceConfiguration
import com.malky.uninotify.presentation.composables.EventDetailsCard
import com.malky.uninotify.presentation.composables.EventInformationCard
import com.malky.uninotify.presentation.composables.HeaderSection
import com.malky.uninotify.presentation.composables.PrimaryButton
import com.malky.uninotify.presentation.composables.SecondaryButton
import com.malky.uninotify.presentation.format
import com.malky.uninotify.presentation.formatTime

@Composable
fun EventDetailsScreen(
    viewModel: EventDetailsViewModel,
    deviceConfiguration: DeviceConfiguration,
    user: FirebaseUser
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    EventDetailsScreenContent(
        state = state,
        onAction = viewModel::onAction,
        deviceConfiguration = deviceConfiguration,
        user = user
    )
}

@Composable
private fun EventDetailsScreenContent(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
        .consumeWindowInsets(WindowInsets.navigationBars),
    state: EventDetailsState,
    onAction: (EventDetailsAction) -> Unit,
    deviceConfiguration: DeviceConfiguration,
    navController: NavHostController = LocalNavController.current,
    user: FirebaseUser
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                LazyColumn(
                    modifier = modifier,
                    verticalArrangement = object : Arrangement.Vertical {
                        override fun Density.arrange(
                            totalSize: Int,
                            sizes: IntArray,
                            outPositions: IntArray
                        ) {
                            var position = 0
                            sizes.forEachIndexed { index, size ->
                                outPositions[index] = position
                                position += size
                            }


                            if (position < totalSize) {
                                val lastIndex = sizes.lastIndex
                                outPositions[lastIndex] = totalSize - sizes[lastIndex]
                                outPositions[lastIndex - 1] =
                                    totalSize - sizes[lastIndex] - sizes[lastIndex - 1]
                            }
                        }

                    },
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item {
                        HeaderSection(
                            modifier = Modifier.padding(bottom = 24.dp),
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
                            profilePic = user.photoUrl.toString()
                        )
                    }
                    item {
                        EventInformationCard(
                            modifier = Modifier
                                .padding(16.dp)
                                .padding(bottom = 16.dp),
                            title = state.event.title,
                            description = state.event.description
                        )
                    }
                    item {
                        EventDetailsCard(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            date = state.event.date.format(),
                            time = state.event.date.formatTime(),
                            venue = state.event.location
                        )
                    }
                    item {
                        PrimaryButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 8.dp)
                                .dropShadow(
                                    shape = RoundedCornerShape(16.dp),
                                    shadow = Shadow(
                                        radius = 1.2.dp,
                                        color = Color.Black.copy(alpha = 0.3f),
                                        offset = DpOffset(x = 0.dp, y = 1.dp)
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
                                .padding(horizontal = 16.dp)
                                .dropShadow(
                                    shape = RoundedCornerShape(16.dp),
                                    shadow = Shadow(
                                        radius = 1.2.dp,
                                        color = Color.Black.copy(alpha = 0.3f),
                                        offset = DpOffset(x = 0.dp, y = 1.dp)
                                    )
                                ),
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

            DeviceConfiguration.MOBILE_LANDSCAPE,
            DeviceConfiguration.FOLDABLE,
            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.LARGE_TABLET,
            DeviceConfiguration.DESKTOP -> {
                LazyVerticalGrid(
                    modifier = modifier.windowInsetsPadding(WindowInsets.displayCutout),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = object : Arrangement.Vertical {
                        override fun Density.arrange(
                            totalSize: Int,
                            sizes: IntArray,
                            outPositions: IntArray
                        ) {
                            var position = 0
                            sizes.forEachIndexed { index, size ->
                                outPositions[index] = position
                                position += size
                            }


                            if (position < totalSize) {
                                val lastIndex = sizes.lastIndex
                                outPositions[lastIndex] = totalSize - sizes[lastIndex]
                            }
                        }

                    },
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item(span = { GridItemSpan(2) }) {
                        HeaderSection(
                            modifier = Modifier.padding(bottom = 24.dp),
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
                            profilePic = user.photoUrl.toString()
                        )
                    }
                    item {
                        EventInformationCard(
                            modifier = Modifier.padding(start = 16.dp, end = 8.dp, bottom = 16.dp),
                            title = state.event.title,
                            description = state.event.description
                        )
                    }
                    item {
                        EventDetailsCard(
                            modifier = Modifier.padding(start = 8.dp, end = 16.dp, bottom = 16.dp),
                            date = state.event.date.format(),
                            time = state.event.date.formatTime(),
                            venue = state.event.location
                        )
                    }
                    item {
                        PrimaryButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 8.dp)
                                .dropShadow(
                                    shape = RoundedCornerShape(16.dp),
                                    shadow = Shadow(
                                        radius = 1.2.dp,
                                        color = Color.Black.copy(alpha = 0.3f),
                                        offset = DpOffset(x = 0.dp, y = 1.dp)
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
                                .padding(horizontal = 16.dp)
                                .dropShadow(
                                    shape = RoundedCornerShape(16.dp),
                                    shadow = Shadow(
                                        radius = 1.2.dp,
                                        color = Color.Black.copy(alpha = 0.3f),
                                        offset = DpOffset(x = 0.dp, y = 1.dp)
                                    )
                                ),
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
    }
}

@Preview(device = "spec:parent=pixel_9,orientation=landscape")
@Composable
private fun PreviewEventDetailsScreen() {
//    UniNotifyTheme {
//        EventDetailsScreenContent(
//            state = EventDetailsState(),
//            onAction = {},
//            deviceConfiguration = DeviceConfiguration.MOBILE_LANDSCAPE
//        )
//    }
}