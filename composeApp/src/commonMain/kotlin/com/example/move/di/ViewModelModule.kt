package com.example.move.di

import com.example.move.ui.movies.MovieListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel {
        MovieListViewModel(
            moviesRepository = get()
        )
    }
}