package com.example.move.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MoviesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    conttent: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppCOlorTheme,
        typography = moveTypography(),
        shapes = AppShapes
    ) {
        conttent()
    }
}
