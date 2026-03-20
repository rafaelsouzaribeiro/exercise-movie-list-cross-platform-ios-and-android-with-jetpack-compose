package com.example.move


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.move.navigation.AppRoutes
import com.example.move.ui.movies.MoveisListRoutes

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = AppRoutes.MovieList
        ) {
            composable<AppRoutes.MovieList> {
                MoveisListRoutes()
            }

            composable<AppRoutes.MovieDetails> {
                // Tela de detalhes usando movieId
            }
        }
    }
}