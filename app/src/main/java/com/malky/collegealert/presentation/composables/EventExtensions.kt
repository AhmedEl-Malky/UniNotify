package com.malky.collegealert.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.composables.icons.lucide.Bell
import com.composables.icons.lucide.BookOpen
import com.composables.icons.lucide.CircleAlert
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Trophy
import com.malky.collegealert.domain.EventType
import com.malky.collegealert.domain.EventType.Exam
import com.malky.collegealert.domain.EventType.Fest
import com.malky.collegealert.domain.EventType.Notice
import com.malky.collegealert.domain.EventType.Seminar

@Composable
fun EventType.eventTheme() : Color{
    return when(this){
        Seminar -> MaterialTheme.colorScheme.surfaceContainerHigh
        Fest -> MaterialTheme.colorScheme.surfaceContainerLow
        Notice -> MaterialTheme.colorScheme.surfaceContainerHighest
        Exam -> MaterialTheme.colorScheme.surfaceContainerLowest
    }
}


@Composable
fun EventType.icon(): ImageVector{
    return when(this){
        Seminar -> Lucide.BookOpen
        Fest -> Lucide.Trophy
        Notice -> Lucide.Bell
        Exam -> Lucide.CircleAlert
    }
}