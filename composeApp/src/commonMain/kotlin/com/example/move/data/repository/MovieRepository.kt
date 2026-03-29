package com.example.move.data.repository

import com.example.move.data.network.KortClient
import com.example.move.data.network.getDefaultLanguageTag
import com.example.move.domain.model.MovieSection
import com.example.move.domain.model.toMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepository(
    private val ioDispatcher: CoroutineDispatcher= Dispatchers.IO,
    private val kortClient: KortClient
) {

    suspend fun getMovieSection(): List<MovieSection>{
        return withContext(ioDispatcher){

           val idiom = getDefaultLanguageTag()

            val popularMoviesDeferred=async{ kortClient.getMovies("popular",idiom) }
            val topRatedMoviesDeferred=async{ kortClient.getMovies("top_rated",idiom) }
            val upComingMoviesDeferred=async{ kortClient.getMovies("upcoming",idiom) }

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