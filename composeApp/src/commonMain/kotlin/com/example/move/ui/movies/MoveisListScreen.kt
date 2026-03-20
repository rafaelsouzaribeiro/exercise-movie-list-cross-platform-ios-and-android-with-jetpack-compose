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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.move.data.repository.MovieRepository
import com.example.move.domain.model.MovieSection
import com.example.move.ui.components.MoviesSection

@Composable
fun MoveisListRoutes(
    viewModel: MovieListViewModel= viewModel {
        MovieListViewModel(
            moviesRepository = MovieRepository()
        )
    }
){
    val moviesListState by viewModel.movieListStates.collectAsStateWithLifecycle()

    MoviesListScreen(
        movieListState = moviesListState
    )
}

@Composable
fun MoviesListScreen(
    movieListState: MovieListViewModel.MoviesListStates
) {
    Scaffold{ paddingValues->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ){
            when(movieListState){
                is MovieListViewModel.MoviesListStates.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is MovieListViewModel.MoviesListStates.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {

                        items(movieListState.movieSections) { Moviesection ->
                            val title = when (Moviesection.sectionType) {
                                MovieSection.SectionType.POPULAR -> "Popular Movies"
                                MovieSection.SectionType.TOP_RATED -> "Top Rated Movies"
                                MovieSection.SectionType.UPCOMING -> "Upcoming Movies"
                            }
                            MoviesSection(
                                title = title,
                                movies = Moviesection.movies
                            )
                        }

                    }
                }
                is MovieListViewModel.MoviesListStates.Error -> {
                    Text(
                        text = movieListState.message,
                        modifier = Modifier.padding(16.dp)
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
