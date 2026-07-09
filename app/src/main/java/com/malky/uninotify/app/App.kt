package com.malky.uninotify.app

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import com.malky.uninotify.app.navigation.NavigationGraph
import com.malky.uninotify.presentation.utils.DeviceConfiguration

@Composable
fun App() {
    val deviceConfiguration =
        DeviceConfiguration.rememberDeviceConfiguration(currentWindowAdaptiveInfo().windowSizeClass)
    NavigationGraph(
        deviceConfiguration = deviceConfiguration
    )
}