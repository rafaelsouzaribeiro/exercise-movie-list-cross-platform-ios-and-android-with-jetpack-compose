package com.example.move.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.move.domain.model.Movie
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.compareTo
import kotlin.text.compareTo

@Composable
fun MoviesSection(
    modifier: Modifier = Modifier,
    onMoviePosterClick:(movieId: Int)-> Unit,
    onLoadMore: () -> Unit,
    movies:List<Movie>,
    title:String,
    isLoadingMore: Boolean
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState, movies.size, isLoadingMore) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1 }
            .distinctUntilChanged()
            .collect { lastVisible ->
                val lastIndex = movies.lastIndex
                if (!isLoadingMore && lastIndex >= 0 && lastVisible >= lastIndex - 2) {
                    onLoadMore()
                }
            }
    }

    Column(modifier = modifier) {
        Text(modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        LazyRow(
            state = listState,
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(movies) { movie ->
                MoviePoster(
                    movie=movie
                    , onMoviePosterClick ={
                        onMoviePosterClick(movie.id)
                    }
                )
            }

            if (isLoadingMore) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}