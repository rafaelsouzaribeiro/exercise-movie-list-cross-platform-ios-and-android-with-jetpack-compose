package com.example.move.di

import com.example.move.data.repository.MovieRepository
import com.example.move.data.repository.MoviesSearchRepository
import org.koin.dsl.module

val repositoryModule= module {
    factory {
        MovieRepository(
            kortClient = get()
        )
    }

    factory {
        MoviesSearchRepository(
            kortClient = get()
        )
    }
}