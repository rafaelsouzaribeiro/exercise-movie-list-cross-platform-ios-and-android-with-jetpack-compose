package com.example.move


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.move.di.kortClientModule
import com.example.move.di.repositoryModule
import com.example.move.di.viewModelModule
import com.example.move.navigation.AppRoutes
import com.example.move.ui.moviedetail.MovieDetailRoute
import com.example.move.ui.movies.MoveisListRoutes
import com.example.move.ui.theme.MoviesAppTheme
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration


@Composable
@Preview
fun App() {
    KoinApplication(configuration = koinConfiguration {
        modules(kortClientModule, repositoryModule, viewModelModule)
    }) {
        MoviesAppTheme {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = AppRoutes.MovieList
            ) {
                composable<AppRoutes.MovieList> {
                    MoveisListRoutes(
                        navegationToMovieDetail = { movieId ->
                            navController.navigate(AppRoutes.MovieDetails(movieId))
                        }
                    )
                }

                composable<AppRoutes.MovieDetails> {
                    MovieDetailRoute()
                }
            }
        }
    }

}