package com.malky.uninotify.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.uninotify.app.theme.CollegeAlertTheme

@Composable
fun AuthenticationHeaderSection(
    title: String,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Stay updated with your campus events",
            style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.outlineVariant)
        )
    }
}

@Preview
@Composable
private fun PreviewAuthenticationHeaderSection() {
    CollegeAlertTheme {
        AuthenticationHeaderSection(
            title = "Login"
        )
    }
}