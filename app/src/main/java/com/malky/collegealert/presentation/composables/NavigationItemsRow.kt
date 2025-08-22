package com.malky.collegealert.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Bell
import com.composables.icons.lucide.Book
import com.composables.icons.lucide.BookOpen
import com.composables.icons.lucide.Bookmark
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.User
import com.malky.collegealert.app.theme.CollegeAlertTheme

@Composable
fun NavigationItemsRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        navigationCards.forEach { (key,value) ->
            NavigationCard(
                modifier = Modifier.weight(1f),
                icon = value,
                label = key,
                onClick = {}
            )
        }
    }
}

@Preview(widthDp = 384)
@Composable
private fun PreviewNavigationItemRow() {
    CollegeAlertTheme {
        NavigationItemsRow()
    }
}

val navigationCards = mapOf(
    "Categories" to Lucide.BookOpen,
    "Saved" to Lucide.Bookmark,
    "Alerts" to Lucide.Bell,
    "Profile" to Lucide.User
)