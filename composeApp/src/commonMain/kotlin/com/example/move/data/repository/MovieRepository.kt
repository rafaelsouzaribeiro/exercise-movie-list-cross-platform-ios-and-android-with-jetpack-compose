package com.example.move.data.repository

import com.example.move.data.network.KortClient
import com.example.move.domain.model.MovieSection
import com.example.move.domain.model.toMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepository(
    private val ioDispatcher: CoroutineDispatcher= Dispatchers.IO
) {

    suspend fun getMovieSection(): List<MovieSection>{
        return withContext(ioDispatcher){
            val popularMoviesDeferred=async{ KortClient.getMovies("popular") }
            val topRatedMoviesDeferred=async{ KortClient.getMovies("top_rated") }
            val upComingMoviesDeferred=async{ KortClient.getMovies("upcoming") }

            val popularMovies=popularMoviesDeferred.await()
            val topRatedMovies=topRatedMoviesDeferred.await()
            val upComingMovies=upComingMoviesDeferred.await()

            listOf(
                MovieSection(
                    sectionType = MovieSection.SectionType.POPULAR,
                    movies = popularMovies.results.map {
                        it.toMovie()
                    }
                ),
                MovieSection(
                    sectionType = MovieSection.SectionType.TOP_RATED,
                    movies = topRatedMovies.results.map {
                        it.toMovie()
                    }
                ),
                MovieSection(
                    sectionType = MovieSection.SectionType.UPCOMING,
                    movies = upComingMovies.results.map {
                        it.toMovie()
                    }
                )
            )
        }
    }
}