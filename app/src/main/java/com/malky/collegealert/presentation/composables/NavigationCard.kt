package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.BookOpen
import com.composables.icons.lucide.Lucide
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.domain.EventType.Exam
import com.malky.collegealert.domain.EventType.Fest
import com.malky.collegealert.domain.EventType.Notice
import com.malky.collegealert.domain.EventType.Seminar

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
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = icon,
                contentDescription = null,
            )
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
    CollegeAlertTheme {
        NavigationCard(
            icon = Lucide.BookOpen,
            label = "Categories",
            onClick = {}
        )
    }
}