package com.malky.uninotify.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.BookOpen
import com.composables.icons.lucide.Lucide
import com.malky.uninotify.app.theme.UniNotifyTheme

@Composable
fun NavigationCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = navigationCardTheme(label).copy(alpha = 0.25f),
            contentColor = navigationCardTheme(label),
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = icon,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun navigationCardTheme(label: String): Color{
    return when(label){
        "Categories" -> MaterialTheme.colorScheme.surfaceContainerHigh
        "Alerts" -> MaterialTheme.colorScheme.surfaceContainerLow
        "Saved" -> MaterialTheme.colorScheme.surfaceContainerHighest
        "Profile" -> MaterialTheme.colorScheme.surfaceContainerLowest
        else -> Color.Transparent
    }
}

@Preview
@Composable
private fun PreviewNavigationCard() {
    UniNotifyTheme {
        NavigationCard(
            icon = Lucide.BookOpen,
            label = "Categories",
            onClick = {}
        )
    }
}