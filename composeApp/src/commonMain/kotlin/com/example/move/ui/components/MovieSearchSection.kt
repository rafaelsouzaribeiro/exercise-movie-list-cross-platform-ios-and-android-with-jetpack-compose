package com.example.move.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.move.domain.model.Movie

@Composable
fun MoviesSearchSection(
    modifier: Modifier = Modifier,
    onMoviePosterClick:(movieId: Int)-> Unit,
    movies:List<Movie>,
    title:String,
) {


    Column(modifier = modifier) {
        Text(modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(movies) { movie ->
                MoviePoster(
                    movie=movie
                    , onMoviePosterClick ={
                        onMoviePosterClick(movie.id)
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }

        }
    }
}