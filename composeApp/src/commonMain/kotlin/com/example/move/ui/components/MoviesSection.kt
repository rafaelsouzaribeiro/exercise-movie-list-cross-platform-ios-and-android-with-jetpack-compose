package com.example.move.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.move.domain.model.Movie

@Composable
fun MoviesSection(
    modifier: Modifier = Modifier,
    movies:List<Movie>,
    title:String
) {
    Column(modifier = modifier) {
        Text(modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        LazyRow(
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(movies) { movie ->
                MoviePoster(movie=movie)
            }
        }
    }
}