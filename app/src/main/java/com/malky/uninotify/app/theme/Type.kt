package com.malky.uninotify.app.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.malky.uninotify.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val Roboto = GoogleFont(name = "Roboto")
val RobotoFontFamily = FontFamily(
    Font(googleFont = Roboto, fontProvider = provider)
)
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.W400
    ),
    displayMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.W400
    ),
    displaySmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.W400
    ),

    // Headline
    headlineLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.W400
    ),
    headlineMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.W400
    ),
    headlineSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.W400
    ),

    // Title
    titleLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.W500
    ),
    titleMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500
    ),
    titleSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500
    ),
    bodyMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500
    ),
    bodySmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W400
    ),

    // Label
    labelLarge = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W500
    ),
    labelMedium = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W500
    ),
    labelSmall = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.W500
    )
)