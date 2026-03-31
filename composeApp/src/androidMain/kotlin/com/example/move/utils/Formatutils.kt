package com.example.move.utils

import java.util.Locale


actual fun Double.FormatRating():String=
    String.format(Locale.getDefault(), "%.1f", this)