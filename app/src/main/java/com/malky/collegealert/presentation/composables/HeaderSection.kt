package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Bookmark
import com.composables.icons.lucide.Calendar
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.User
import com.malky.collegealert.app.theme.CollegeAlertTheme

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    navigationIcon: @Composable () -> Unit = {},
    profileButton: @Composable () -> Unit = {},
    extraContent: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    color = Color.Black.copy(alpha = 0.5f),
                    offset = DpOffset(x = 0.dp, y = 0.5.dp)
                )
            )
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationIcon()
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.75f
                        )
                    )
                )
            }
            profileButton()
        }
        extraContent()
    }
}

@Preview(widthDp = 384, showBackground = true)
@Composable
private fun PreviewHeaderSection() {
    CollegeAlertTheme {
        HeaderSection(
            extraContent = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    EventsCountCard(
                        modifier = Modifier.weight(1f),
                        title = "Upcoming Events",
                        eventsCount = 4,
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
                        eventsCount = 0,
                        icon = {
                            Icon(
                                imageVector = Lucide.Bookmark,
                                contentDescription = "Calendar Icon",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            },
            title = "Good Morning!",
            subtitle = "Stay updated with campus events",
            profileButton = {
                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Lucide.User,
                        contentDescription = "Go to Profile",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        )
    }
}