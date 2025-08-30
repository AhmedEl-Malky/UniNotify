package com.malky.collegealert.presentation.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import com.malky.collegealert.app.navigation.LocalNavController
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.DeviceConfiguration
import com.malky.collegealert.presentation.composables.AppLogo
import com.malky.collegealert.presentation.composables.AuthenticationHeaderSection
import com.malky.collegealert.presentation.composables.LoginFormSection

@Composable
fun LoginScreen(
    windowSize: WindowSizeClass,
    viewModel: AuthenticationViewModel
) {
    LoginScreenContent(
        windowSize = windowSize
    )
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier
        .background(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        )
        .padding(horizontal = 16.dp, vertical = 24.dp),
    windowSize: WindowSizeClass,
    navController: NavHostController = LocalNavController.current
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            AppLogo(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )
            when (DeviceConfiguration.rememberDeviceConfiguration(windowSize)) {
                DeviceConfiguration.MOBILE_PORTRAIT -> {
                    Column(
                        modifier = modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                    ) {
                        AuthenticationHeaderSection(title = "Login")
                        LoginFormSection(
                            modifier = Modifier.fillMaxWidth(),
                            navController = navController
                        )
                    }
                }

                DeviceConfiguration.MOBILE_LANDSCAPE -> {
                    Row(
                        modifier = modifier
                            .windowInsetsPadding(WindowInsets.displayCutout)
                            .weight(1f)
                            .padding(horizontal = 24.dp)
                            .verticalScroll(rememberScrollState()),
                    ) {
                        AuthenticationHeaderSection(
                            modifier = Modifier.weight(1f),
                            title = "Login"
                        )
                        LoginFormSection(
                            modifier = Modifier.weight(1f),
                            navController = navController
                        )
                    }
                }

                DeviceConfiguration.FOLDABLE,
                DeviceConfiguration.TABLET_LANDSCAPE,
                DeviceConfiguration.TABLET_PORTRAIT,
                DeviceConfiguration.LARGE_TABLET,
                DeviceConfiguration.DESKTOP -> {
                    Column(
                        modifier = modifier
                            .weight(1f)
                            .padding(vertical = 12.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AuthenticationHeaderSection(
                            modifier = Modifier.widthIn(max = 540.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            title = "Login"
                        )
                        LoginFormSection(
                            modifier = Modifier.widthIn(max = 540.dp),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = "spec:parent=pixel_9,orientation=landscape")
@Composable
private fun PreviewLoginScreen() {
    CollegeAlertTheme {
//        LoginScreenContent()
    }
}