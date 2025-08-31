package com.malky.collegealert.presentation

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

enum class DeviceConfiguration {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    FOLDABLE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    LARGE_TABLET,
    DESKTOP;

    companion object {
        fun rememberDeviceConfiguration(windowSize: WindowSizeClass): DeviceConfiguration {
            val screenWidth = windowSize.windowWidthSizeClass
            val screenHeight = windowSize.windowHeightSizeClass
            return when {
                screenWidth == WindowWidthSizeClass.COMPACT && screenHeight == WindowHeightSizeClass.MEDIUM -> MOBILE_PORTRAIT
                screenWidth == WindowWidthSizeClass.COMPACT && screenHeight == WindowHeightSizeClass.EXPANDED -> MOBILE_PORTRAIT
                screenWidth == WindowWidthSizeClass.MEDIUM && screenHeight == WindowHeightSizeClass.COMPACT -> MOBILE_LANDSCAPE
                screenWidth == WindowWidthSizeClass.EXPANDED && screenHeight == WindowHeightSizeClass.COMPACT -> MOBILE_LANDSCAPE

                screenWidth == WindowWidthSizeClass.MEDIUM && screenHeight == WindowHeightSizeClass.EXPANDED -> TABLET_PORTRAIT
                screenWidth == WindowWidthSizeClass.EXPANDED && screenHeight == WindowHeightSizeClass.MEDIUM -> TABLET_LANDSCAPE

                screenWidth == WindowWidthSizeClass.EXPANDED && screenHeight == WindowHeightSizeClass.COMPACT -> FOLDABLE

                else -> DESKTOP

            }
        }
    }

}


