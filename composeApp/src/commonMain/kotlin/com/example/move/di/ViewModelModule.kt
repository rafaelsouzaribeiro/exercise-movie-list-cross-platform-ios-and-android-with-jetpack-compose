package com.example.move.di

import com.example.move.ui.moviedetail.MovieDetailViewModel
import com.example.move.ui.movies.MovieListViewModel
import com.example.move.ui.search.MoviesSearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel {
        MovieListViewModel(
            moviesRepository = get()
        )
    }

    viewModel {
        MovieDetailViewModel(
            savedStateHandle = get(),
            movieRepository = get()
        )
    }

    viewModel {
        MoviesSearchViewModel(
            moviesRepository = get()
        )
    }
}