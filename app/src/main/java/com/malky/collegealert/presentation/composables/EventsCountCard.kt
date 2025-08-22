package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Lucide
import com.malky.collegealert.app.theme.CollegeAlertTheme

@Composable
fun EventsCountCard(
    modifier: Modifier = Modifier,
    title: String,
    eventsCount: Int,
    icon : @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 36.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon()
            Text(
                text = eventsCount.toString(),
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f))
            )
        }
    }
}

@Preview
@Composable
private fun PreviewEventsCountCard() {
    CollegeAlertTheme {
        EventsCountCard(
            eventsCount = 4,
            title = "Upcoming Events",
            icon = {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Lucide.Calendar,
                    contentDescription = "Calendar Icon"
                )
            }
        )
    }
}