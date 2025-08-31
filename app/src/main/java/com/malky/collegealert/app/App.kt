package com.malky.collegealert.app

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import com.malky.collegealert.app.navigation.NavigationGraph
import com.malky.collegealert.presentation.DeviceConfiguration

@Composable
fun App() {
    val deviceConfiguration =
        DeviceConfiguration.rememberDeviceConfiguration(currentWindowAdaptiveInfo().windowSizeClass)
    NavigationGraph(
        deviceConfiguration = deviceConfiguration
    )
}