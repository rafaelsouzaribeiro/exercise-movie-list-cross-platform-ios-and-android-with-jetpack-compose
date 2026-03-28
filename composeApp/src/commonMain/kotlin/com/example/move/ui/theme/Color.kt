package com.example.move.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val primary80 = Color(0xFF2A2A2A)
val backGourndColor = Color(0xFF0F1115)
val surfaceDark = Color(0xFF1A1D24)
val textPrimaryDark = Color(0xFFF5F7FA)
val colorError = Color(0xFFFF6B6B)
val neutral60 = Color(0xFFB0B7C3)


internal val AppCOlorTheme= darkColorScheme(
    primary = primary80,
    background = backGourndColor,
    surface = surfaceDark,
    secondary = neutral60,
    onPrimary = textPrimaryDark,
    onBackground = textPrimaryDark,
    onSurface = textPrimaryDark,
    error = colorError
)