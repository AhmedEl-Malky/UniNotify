package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.BookOpen
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Lucide
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.domain.EventType

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    description: String,
    eventType: EventType,
    date: String,
    time: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .dropShadow(
                shape = RoundedCornerShape(16.dp),
                shadow = Shadow(
                    radius = 2.dp,
                    color = Color.Black.copy(alpha = 0.3f),
                    offset = DpOffset(x = 0.dp, y = 0.5.dp)
                )
            ),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 40.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .background(
                        color = eventType.eventTheme().copy(alpha = 0.15f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .size(40.dp)
                    .padding(10.dp),
                imageVector = eventType.icon(),
                contentDescription = "$eventType Event",
                tint = eventType.eventTheme()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600
                    )
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.75f
                        )
                    )
                )
                Spacer(modifier.height(2.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Lucide.Calendar,
                        contentDescription = "Event Date",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = date,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.75f
                            )
                        )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.75f
                            )
                        )
                    )
                }
            }

        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}


@Preview
@Composable
private fun PreviewEventCard() {
    CollegeAlertTheme {
        EventCard(
            onClick = {},
            title = "AI & Machine Learning Seminar",
            description = "Learn about the latest trends in AI and ML from industry experts.",
            date = "1/15/2024",
            time = "10:00 AM",
            eventType = EventType.Seminar
        )
    }
}