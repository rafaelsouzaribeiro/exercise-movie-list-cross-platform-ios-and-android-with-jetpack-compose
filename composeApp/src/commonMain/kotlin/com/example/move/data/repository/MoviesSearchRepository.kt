package com.example.move.data.repository

import com.example.move.data.mapper.toModel
import com.example.move.data.network.KortClient
import com.example.move.domain.model.MovieSectionSearch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MoviesSearchRepository(
    private val ioDispatcher: CoroutineDispatcher= Dispatchers.IO,
    private val kortClient: KortClient
) {

    suspend fun getSearchMovies(query:String,page: Int): MovieSectionSearch{
        return withContext(ioDispatcher){

            val searchMoviesDeferred=async{ kortClient.getSearchMovies(query,page) }

            val searchMovies=searchMoviesDeferred.await()

            MovieSectionSearch(
                    sectionType = MovieSectionSearch.SectionType.SEARCH,
                    movies = searchMovies.results.map {
                        it.toModel()
                    }
                )
        }
    }

}