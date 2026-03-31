package com.example.move.ui.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.move.data.repository.MovieRepository
import com.example.move.domain.model.Movie
import com.example.move.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val movieDetailRoute = savedStateHandle.toRoute<AppRoutes.MovieDetails>()

    private val _movieDetailState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetailState = _movieDetailState.asStateFlow()
    init{
        getMovieDetail()
    }
    private fun getMovieDetail(){
        viewModelScope.launch {
            movieRepository.getMovieDetail(movieDetailRoute.id).fold(
                onSuccess = { movie ->
                    _movieDetailState.update {
                        MovieDetailState.Success(movie)
                    }
                },
                onFailure = { error ->
                    _movieDetailState.update {
                        MovieDetailState.Error(error.message ?: "Unknown error")
                    }
                }
            )

        }
    }
    sealed interface MovieDetailState {
        data class Success(val movieDetail: Movie) : MovieDetailState
        data object Loading : MovieDetailState
        data class Error(val message: String) : MovieDetailState
    }
}