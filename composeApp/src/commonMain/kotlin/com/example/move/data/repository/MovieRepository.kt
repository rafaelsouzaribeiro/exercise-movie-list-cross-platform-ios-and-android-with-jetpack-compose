package com.example.move.data.repository

import com.example.move.data.mapper.toModel
import com.example.move.data.network.KortClient
import com.example.move.domain.model.ImageSize
import com.example.move.domain.model.Movie
import com.example.move.domain.model.MovieSection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepository(
    private val ioDispatcher: CoroutineDispatcher= Dispatchers.IO,
    private val kortClient: KortClient
) {

    suspend fun getMovieUPComing(page: Int): MovieSection{
        return withContext(ioDispatcher){

            val upComingMoviesDeferred=async{ kortClient.getMovies("upcoming",page) }

            val upComingMovies=upComingMoviesDeferred.await()

                MovieSection(
                    sectionType = MovieSection.SectionType.UPCOMING,
                    movies = upComingMovies.results.map {
                        it.toModel()
                    }
                )
        }
    }

    suspend fun getMoviePopular(page: Int): MovieSection{
        return withContext(ioDispatcher){

            val popularMoviesDeferred=async{ kortClient.getMovies("popular",page) }

            val popularMovies=popularMoviesDeferred.await()

                MovieSection(
                    sectionType = MovieSection.SectionType.POPULAR,
                    movies = popularMovies.results.map {
                        it.toModel()
                    }
                )
        }
    }


    suspend fun getMovieTopRated(page:Int): MovieSection{
        return withContext(ioDispatcher){

            val topRatedMoviesDeferred=async{ kortClient.getMovies("top_rated",page) }
            val topRatedMovies=topRatedMoviesDeferred.await()

                MovieSection(
                    sectionType = MovieSection.SectionType.TOP_RATED,
                    movies = topRatedMovies.results.map {
                        it.toModel()
                    }
                )
        }
    }

    suspend fun getMovieDetail(moveId:Int): Result<Movie>{
        return withContext(ioDispatcher){
            runCatching {
                val movieDetailDeferred= async{kortClient.getMovieDetail(moveId)}
                val creditsDeferred=async{kortClient.getCredits(moveId)}
                val videoDeffered=async{kortClient.getVideos(moveId)}

                val videoResponse=videoDeffered.await()
                val movieDetailsResponse = movieDetailDeferred.await()
                val creditsResponse = creditsDeferred.await()

                val movieTrailerYoutubeKey = videoResponse.results
                    .firstOrNull { it.site=="YouTube" }
                    ?.key


                movieDetailsResponse.toModel(
                    castMemberResponse = creditsResponse.cast,
                    movieTrailerYoutubeKey  = movieTrailerYoutubeKey,
                    imageSize = ImageSize.X_LARGE,
                )
            }
        }
    }

}