package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
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
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Clock
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MapPin
import com.malky.collegealert.app.theme.CollegeAlertTheme
import com.malky.collegealert.app.theme.Green

@Composable
fun EventDetailsCard(
    date: String,
    time: String,
    venue: String,
    modifier: Modifier = Modifier
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .size(40.dp)
                        .padding(10.dp),
                    imageVector = Lucide.Calendar,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceContainerHigh
                )
                Column {
                    Text(
                        modifier = Modifier.paddingFromBaseline(bottom = 8.dp),
                        text = "Date",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = date,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.75f
                            )
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .size(40.dp)
                        .padding(10.dp),
                    imageVector = Lucide.Clock,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceContainerHighest
                )
                Column {
                    Text(
                        modifier = Modifier.paddingFromBaseline(bottom = 8.dp),
                        text = "Time",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = time,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.75f
                            )
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .background(
                            color = Green.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .size(40.dp)
                        .padding(10.dp),
                    imageVector = Lucide.MapPin,
                    contentDescription = null,
                    tint = Green
                )
                Column {
                    Text(
                        modifier = Modifier.paddingFromBaseline(bottom = 8.dp),
                        text = "Venue",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = venue,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.75f
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewEventDetailsCard() {
    CollegeAlertTheme {
        EventDetailsCard(
            date = "Monday, January 15, 2024",
            time = "10:00 AM",
            venue = "Seminar Hall A"
        )
    }
}