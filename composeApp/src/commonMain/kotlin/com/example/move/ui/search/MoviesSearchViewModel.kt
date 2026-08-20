package com.example.move.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.move.data.repository.MoviesSearchRepository
import com.example.move.domain.model.MovieSectionSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesSearchViewModel(
    private val moviesRepository: MoviesSearchRepository
) : ViewModel() {
    private val _moviesSearch =
        MutableStateFlow<MoviesSearchStates>(MoviesSearchStates.Loading)
    val moviesSearch = _moviesSearch.asStateFlow()

    fun searchMovies(query:String){
        viewModelScope.launch {
            _moviesSearch.value = MoviesSearchStates.Loading
            try {
                val movies = moviesRepository.getSearchMovies(query, 1)
                _moviesSearch.value = MoviesSearchStates.Success(movies)
            }catch (e: Exception) {
                _moviesSearch.value = MoviesSearchStates.Error(
                    e.message ?: "An unexpected error occurred"
                )
            }
        }
    }

    sealed interface MoviesSearchStates {
        data class Success(val movies: MovieSectionSearch) : MoviesSearchStates
        data object Loading : MoviesSearchStates
        data class Error(val message: String) : MoviesSearchStates
    }
}
