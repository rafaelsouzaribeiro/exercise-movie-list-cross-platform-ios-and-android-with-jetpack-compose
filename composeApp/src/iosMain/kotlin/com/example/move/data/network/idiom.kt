package com.example.move.data.network

import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

actual fun getDefaultLanguageTag(): String {
    val tag = (NSLocale.preferredLanguages.firstOrNull() as? String)?.lowercase() ?: "en"
    return when {
        tag.startsWith("pt") -> "pt-BR"
        else -> "en-US"
    }
}

