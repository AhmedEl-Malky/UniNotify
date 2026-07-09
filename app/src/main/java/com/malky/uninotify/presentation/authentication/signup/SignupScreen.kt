package com.malky.uninotify.presentation.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.malky.uninotify.app.navigation.LocalNavController
import com.malky.uninotify.presentation.theme.UniNotifyTheme
import com.malky.uninotify.presentation.utils.DeviceConfiguration
import com.malky.uninotify.presentation.composables.AppLogo
import com.malky.uninotify.presentation.composables.AuthenticationHeaderSection
import com.malky.uninotify.presentation.composables.SignupForm
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(
    deviceConfiguration: DeviceConfiguration,
    viewModel: SignupViewModel,
    updateUser:() -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SignupScreenContent(
        deviceConfiguration = deviceConfiguration,
        state = state,
        onAction = viewModel::onAction,
        updateUser = updateUser
    )
}

@Composable
private fun SignupScreenContent(
    modifier: Modifier = Modifier
        .background(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        )
        .padding(horizontal = 16.dp, vertical = 24.dp),
    deviceConfiguration: DeviceConfiguration,
    state: SignupState,
    onAction: (SignupAction) -> Unit,
    updateUser:() -> Unit,
    navController: NavHostController = LocalNavController.current
) {
    val snackBarHost = remember{ SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    state.error?.asString()?.let { error ->
        coroutineScope.launch{
            snackBarHost.showSnackbar(
                message = error,
                actionLabel = "Dismiss",
                duration = SnackbarDuration.Short
            )
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets.statusBars,
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHost
            ){ it ->
                Snackbar(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.navigationBars),
                    snackbarData = it,
                    shape = RoundedCornerShape(8.dp),
                    contentColor = MaterialTheme.colorScheme.error,
                    containerColor = MaterialTheme.colorScheme.background,
                    actionColor = MaterialTheme.colorScheme.primary,
                    actionContentColor = MaterialTheme.colorScheme.primary
                )
            }
        }
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
            when (deviceConfiguration) {
                DeviceConfiguration.MOBILE_PORTRAIT -> {
                    Column(
                        modifier = modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                    ) {
                        AuthenticationHeaderSection(title = "Signup")
                        SignupForm(
                            modifier = Modifier.fillMaxWidth(),
                            navController = navController,
                            state = state,
                            onAction = onAction,
                            updateUser = updateUser
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
                            title = "Signup"
                        )
                        SignupForm(
                            modifier = Modifier.weight(1f),
                            navController = navController,
                            state = state,
                            onAction = onAction,
                            updateUser = updateUser
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
                            title = "Signup"
                        )
                        SignupForm(
                            modifier = Modifier.widthIn(max = 540.dp),
                            navController = navController,
                            state = state,
                            onAction = onAction,
                            updateUser = updateUser
                        )
                    }
                }
            }

        }
    }
}

@Preview(showSystemUi = true, device = "spec:parent=pixel_9,orientation=portrait")
@Composable
private fun PreviewSignupScreen() {
    UniNotifyTheme {
//        SignupScreenContent()
    }
}