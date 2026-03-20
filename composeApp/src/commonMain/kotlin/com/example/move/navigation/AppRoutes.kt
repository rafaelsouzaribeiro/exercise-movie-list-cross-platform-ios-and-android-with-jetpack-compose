package com.example.move.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes{
    @Serializable
    data object MovieList: AppRoutes
    @Serializable
    data class MovieDetails(val id: Int): AppRoutes
}