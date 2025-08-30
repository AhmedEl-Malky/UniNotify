package com.malky.collegealert.app

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import com.malky.collegealert.app.navigation.NavigationGraph

@Composable
fun App() {
    val windowSize = currentWindowAdaptiveInfo().windowSizeClass
    NavigationGraph(
        windowSize = windowSize
    )
}