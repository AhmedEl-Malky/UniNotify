package com.malky.collegealert.presentation.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.malky.collegealert.app.navigation.LocalNavController
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.presentation.composables.AuthenticationHeaderSection
import com.malky.collegealert.presentation.composables.LoginFormSection

@Composable
fun LoginScreen(
    viewModel: AuthenticationViewModel
) {
    LoginScreenContent()
}

@Composable
private fun LoginScreenContent(
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
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthenticationHeaderSection(title = "Login")
            LoginFormSection(
                modifier = Modifier.fillMaxWidth(),
                navController = navController
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginScreen() {
    CollegeAlertTheme {
        LoginScreenContent()
    }
}