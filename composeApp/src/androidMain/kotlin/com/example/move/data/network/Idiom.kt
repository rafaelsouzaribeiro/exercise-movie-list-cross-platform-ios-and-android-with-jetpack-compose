package com.example.move.data.network

import androidx.compose.ui.text.intl.Locale

actual fun getDefaultLanguageTag(): String {
    val lang = Locale.current.language
    val normalizedLang = lang.replace('_', '-').lowercase()
    return when {
        normalizedLang.startsWith("pt") -> "pt-BR"
        else -> "en-US"
    }
}