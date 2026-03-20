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
        getMovieSection()
    }
    private val _movieListState = MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStates=_movieListState.asStateFlow()

    private fun getMovieSection(){
       viewModelScope.launch {
           try {
               val movieSections=moviesRepository.getMovieSection()
               _movieListState.update {
                     MoviesListStates.Success(movieSections)
               }
           }catch (e: Exception){
               _movieListState.update {
                     MoviesListStates.Error(e.message ?: "An unexpected error occurred")
               }
           }
       }
    }
    sealed interface MoviesListStates{
        data class Success(val movieSections: List<MovieSection >): MoviesListStates
        data object Loading: MoviesListStates
        data class Error(val message: String): MoviesListStates
    }
}