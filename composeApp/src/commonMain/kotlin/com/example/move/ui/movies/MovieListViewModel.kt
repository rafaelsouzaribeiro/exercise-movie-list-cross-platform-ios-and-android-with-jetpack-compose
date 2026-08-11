package com.example.move.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.move.data.repository.MovieRepository
import com.example.move.domain.model.MovieSection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val moviesRepository: MovieRepository
) : ViewModel() {
    private val _movieListStatePopular =
        MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStatePopular = _movieListStatePopular.asStateFlow()

    private val _movieListStateTopRated =
        MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStateTopRated = _movieListStateTopRated.asStateFlow()

    private val _movieListStateUpComing =
        MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStateUpComing = _movieListStateUpComing.asStateFlow()

    init {
        loadPopularInitial()
        loadTopRatedInitial()
        loadUpComingInitial()
    }

    fun onLoadMore(sectionType: MovieSection.SectionType) {
        when (sectionType) {
            MovieSection.SectionType.POPULAR -> loadNextPage(
                stateFlow = _movieListStatePopular,
                fetch = { page -> moviesRepository.getMoviePopular(page) }
            )

            MovieSection.SectionType.TOP_RATED -> loadNextPage(
                stateFlow = _movieListStateTopRated,
                fetch = { page -> moviesRepository.getMovieTopRated(page) }
            )

            MovieSection.SectionType.UPCOMING -> loadNextPage(
                stateFlow = _movieListStateUpComing,
                fetch = { page -> moviesRepository.getMovieUPComing(page) }
            )
        }
    }

    private fun loadPopularInitial() = loadInitial(
        stateFlow = _movieListStatePopular,
        fetch = { page -> moviesRepository.getMoviePopular(page) }
    )

    private fun loadTopRatedInitial() = loadInitial(
        stateFlow = _movieListStateTopRated,
        fetch = { page -> moviesRepository.getMovieTopRated(page) }
    )

    private fun loadUpComingInitial() = loadInitial(
        stateFlow = _movieListStateUpComing,
        fetch = { page -> moviesRepository.getMovieUPComing(page) }
    )

    private fun loadInitial(
        stateFlow: MutableStateFlow<MoviesListStates>,
        fetch: suspend (page: Int) -> MovieSection
    ) {
        viewModelScope.launch {
            stateFlow.value = MoviesListStates.Loading
            try {
                val section = fetch(1)
                stateFlow.value = MoviesListStates.Success(
                    movieSections = section,
                    currentPage = 1,
                    isLoadingMore = false,
                    endReached = section.movies.isEmpty()
                )
            } catch (e: Exception) {
                stateFlow.value =
                    MoviesListStates.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

    private fun loadNextPage(
        stateFlow: MutableStateFlow<MoviesListStates>,
        fetch: suspend (page: Int) -> MovieSection
    ) {
        val current = stateFlow.value as? MoviesListStates.Success ?: return
        if (current.isLoadingMore || current.endReached) return

        viewModelScope.launch {
            stateFlow.update { (it as MoviesListStates.Success).copy(isLoadingMore = true) }

            try {
                val nextPage = current.currentPage + 1
                val nextSection = fetch(nextPage)

                stateFlow.update {
                    val success = it as MoviesListStates.Success
                    success.copy(
                        movieSections = success.movieSections.copy(
                            movies = success.movieSections.movies + nextSection.movies
                        ),
                        currentPage = nextPage,
                        isLoadingMore = false
                    )
                }
            } catch (_: Exception) {
                stateFlow.update {
                    (it as MoviesListStates.Success).copy(isLoadingMore = false)
                }
            }
        }
    }

    sealed interface MoviesListStates {
        data class Success(
            val movieSections: MovieSection,
            val currentPage: Int,
            val isLoadingMore: Boolean,
            val endReached: Boolean
        ) : MoviesListStates

        data object Loading : MoviesListStates
        data class Error(val message: String) : MoviesListStates
    }
}
