package com.example.move.di

import com.example.move.data.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule= module {
    factory {
        MovieRepository(
            kortClient = get()
        )
    }
}