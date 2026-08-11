package com.example.move.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.move.domain.model.MovieSection
import com.example.move.ui.components.MoviesSection
import move.composeapp.generated.resources.Res
import move.composeapp.generated.resources.movies_list_popular_movies
import move.composeapp.generated.resources.movies_list_top_rated_movies
import move.composeapp.generated.resources.movies_list_upcoming_movies
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoveisListRoutes(
    viewModel: MovieListViewModel= koinViewModel(),
    navegationToMovieDetail:(movieId:Int)-> Unit
){
    val moviesListStatePopular by viewModel.movieListStatePopular.collectAsStateWithLifecycle()
    val moviesListStateTopRated by viewModel.movieListStateTopRated.collectAsStateWithLifecycle()
    val moviesListStateUpComing by viewModel.movieListStateUpComing.collectAsStateWithLifecycle()

    MoviesListScreen(
        movieListState = listOf(
            moviesListStatePopular,
            moviesListStateTopRated,
            moviesListStateUpComing),
        onMovieClick =navegationToMovieDetail
    )
}

@Composable
fun MoviesListScreen(
    movieListState: List<MovieListViewModel.MoviesListStates>,
    onMovieClick: (movieId: Int) -> Unit,
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(movieListState) { state ->
                when (state) {
                    is MovieListViewModel.MoviesListStates.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is MovieListViewModel.MoviesListStates.Success -> {
                        val title = when (state.movieSections.sectionType) {
                            MovieSection.SectionType.POPULAR ->
                                stringResource(Res.string.movies_list_popular_movies)

                            MovieSection.SectionType.TOP_RATED ->
                                stringResource(Res.string.movies_list_top_rated_movies)

                            MovieSection.SectionType.UPCOMING ->
                                stringResource(Res.string.movies_list_upcoming_movies)
                        }

                        MoviesSection(
                            title = title,
                            movies = state.movieSections.movies,
                            onMoviePosterClick = onMovieClick
                        )
                    }

                    is MovieListViewModel.MoviesListStates.Error -> {
                        Text(
                            text = state.message,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


