package com.malky.uninotify.app.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = PrimaryForeground,
    secondary = Secondary,
    onSecondary = SecondaryForeground,
    background = Background,
    onBackground = Foreground,
    surface = Sidebar,
    onSurface = SidebarForeground,
    error = Destructive,
    onError = DestructiveForeground,
    outline = Border,
    outlineVariant = Ring,
    scrim = Input,
    surfaceContainerHigh = Chart1,
    surfaceContainerHighest = Chart2,
    surfaceContainerLow = Chart3,
    surfaceContainerLowest = Chart4,
)

@Composable
fun UniNotifyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}