package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Bookmark
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Lucide
import com.malky.collegealert.app.theme.CollegeAlertTheme

@Composable
fun EventsCountSection(
    upcomingEventsCount: Int,
    savedEventsCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        EventsCountCard(
            modifier = Modifier.weight(1f),
            title = "Upcoming Events",
            eventsCount = upcomingEventsCount,
            icon = {
                Icon(
                    imageVector = Lucide.Calendar,
                    contentDescription = "Calendar Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
        EventsCountCard(
            modifier = Modifier.weight(1f),
            title = "Saved Events",
            eventsCount = savedEventsCount,
            icon = {
                Icon(
                    imageVector = Lucide.Bookmark,
                    contentDescription = "Calendar Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@Preview
@Composable
private fun PreviewEventsCountSection() {
    CollegeAlertTheme {
        EventsCountSection(
            upcomingEventsCount = 4,
            savedEventsCount = 0
        )
    }
}