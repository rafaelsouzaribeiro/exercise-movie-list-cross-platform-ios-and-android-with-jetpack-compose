package com.example.move.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import move.composeapp.generated.resources.Res
import move.composeapp.generated.resources.Urbanist_Bold
import move.composeapp.generated.resources.Urbanist_Medium
import move.composeapp.generated.resources.Urbanist_Regular
import org.jetbrains.compose.resources.Font

val ubernameFontFamily : FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.Urbanist_Regular, FontWeight.Normal)
        ,Font(Res.font.Urbanist_Medium, FontWeight.Medium)
        ,Font(Res.font.Urbanist_Bold, FontWeight.Bold)
    )

@Composable
fun moveTypography() = Typography(
    displaySmall = TextStyle(
        fontFamily = ubernameFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = ubernameFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = ubernameFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = ubernameFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = ubernameFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = ubernameFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = ubernameFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = ubernameFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp)
)