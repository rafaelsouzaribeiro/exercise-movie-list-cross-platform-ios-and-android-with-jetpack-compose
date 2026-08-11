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
): ViewModel() {
    init {
        getMoviePopular()
        getMovieTopRated()
        getMovieUpComing()
    }
    private val _movieListStatePopular = MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStatePopular=_movieListStatePopular.asStateFlow()

    private val _movieListStateTopRated = MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStateTopRated=_movieListStateTopRated.asStateFlow()

    private val _movieListStateUpComing = MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStateUpComing=_movieListStateUpComing.asStateFlow()

    private fun getMoviePopular(){
       viewModelScope.launch {
           try {
               val movieSections=moviesRepository.getMoviePopular()
               _movieListStatePopular.update {
                     MoviesListStates.Success(movieSections)
               }
           }catch (e: Exception){
               _movieListStatePopular.update {
                     MoviesListStates.Error(e.message ?: "An unexpected error occurred")
               }
           }
       }
    }

    private fun getMovieTopRated(){
        viewModelScope.launch {
            try {
                val movieSections=moviesRepository.getMovieTopRated()
                _movieListStateTopRated.update {
                    MoviesListStates.Success(movieSections)
                }
            }catch (e: Exception){
                _movieListStateTopRated.update {
                    MoviesListStates.Error(e.message ?: "An unexpected error occurred")
                }
            }
        }
    }

    private fun getMovieUpComing(){
        viewModelScope.launch {
            try {
                val movieSections=moviesRepository.getMovieUPComing()
                _movieListStateUpComing.update {
                    MoviesListStates.Success(movieSections)
                }
            }catch (e: Exception){
                _movieListStateUpComing.update {
                    MoviesListStates.Error(e.message ?: "An unexpected error occurred")
                }
            }
        }
    }
    sealed interface MoviesListStates{
        data class Success(val movieSections: MovieSection): MoviesListStates
        data object Loading: MoviesListStates
        data class Error(val message: String): MoviesListStates
    }
}