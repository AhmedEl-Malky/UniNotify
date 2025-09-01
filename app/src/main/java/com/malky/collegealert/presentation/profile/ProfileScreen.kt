package com.malky.collegealert.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.LogOut
import com.composables.icons.lucide.Lucide
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.DeviceConfiguration
import com.malky.collegealert.presentation.composables.ContactInformationCard
import com.malky.collegealert.presentation.composables.HeaderSection
import com.malky.collegealert.presentation.composables.PersonalInformationCard
import com.malky.collegealert.presentation.composables.SecondaryButton
import com.malky.collegealert.presentation.composables.SettingsCard

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    deviceConfiguration: DeviceConfiguration
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProfileScreenContent(
        state = state,
        onAction = viewModel::onAction,
        deviceConfiguration = deviceConfiguration
    )
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
    state: ProfileState,
    onAction: (ProfileAction) -> Unit,
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
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item {
                        HeaderSection(
                            modifier = Modifier.padding(bottom = 8.dp),
                            title = "Profile",
                            subtitle = "Manage your account",
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
                    item {
                        PersonalInformationCard(
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    item {
                        ContactInformationCard(
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    item {
                        SettingsCard()
                    }
                    item {
                        SecondaryButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .padding(horizontal = 16.dp),
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.error.copy(alpha = 0.3f)
                            )
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(16.dp),
                                imageVector = Lucide.LogOut,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = "Log out",
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
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
                                outPositions[lastIndex - 1] =
                                    totalSize - sizes[lastIndex] - sizes[lastIndex - 1]
                            }
                        }
                    },
                    horizontalArrangement = Arrangement.Center,
                    contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 4.dp)
                ) {
                    item(span = { GridItemSpan(2) }) {
                        HeaderSection(
                            modifier = Modifier.padding(bottom = 24.dp),
                            title = "Profile",
                            subtitle = "Manage your account",
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
                    item {
                        PersonalInformationCard(
                            modifier = Modifier.padding(start = 16.dp, end = 8.dp, bottom = 16.dp)
                        )
                    }
                    item {
                        ContactInformationCard(
                            modifier = Modifier.padding(start = 8.dp, end = 16.dp, bottom = 16.dp)
                        )
                    }
                    item(span = { GridItemSpan(2) }) {
                        SettingsCard(modifier = Modifier.padding(bottom = 16.dp))
                    }
                    item(span = { GridItemSpan(2) }) {
                        SecondaryButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .padding(horizontal = 16.dp),
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.error.copy(alpha = 0.3f)
                            )
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(16.dp),
                                imageVector = Lucide.LogOut,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = "Log out",
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
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
private fun PreviewProfileScreen() {
    CollegeAlertTheme {
        ProfileScreenContent(
            state = ProfileState(),
            onAction = {},
            deviceConfiguration = DeviceConfiguration.MOBILE_LANDSCAPE
        )
    }
}