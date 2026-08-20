package com.example.move.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.move.ui.components.MoviesSearchSection
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import move.composeapp.generated.resources.Res
import move.composeapp.generated.resources.movies_search
import move.composeapp.generated.resources.movies_search_input
import move.composeapp.generated.resources.movies_search_result
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoveisSearchRoutes(
    navegationToMovieDetail: (movieId: Int) -> Unit,
    onNavegationIconClick: () -> Unit,
) {
    MoviesSearchScreen(
        onMovieClick = navegationToMovieDetail,
        onNavegationIconClick = onNavegationIconClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesSearchScreen(
    viewModel: MoviesSearchViewModel = koinViewModel(),
    onMovieClick: (movieId: Int) -> Unit,
    onNavegationIconClick: () -> Unit,
) {
    var query by remember { mutableStateOf("") }

    val movieListState by viewModel.moviesSearch.collectAsStateWithLifecycle()

    LaunchedEffect(query) {
        if (query.isNotBlank()) {
            viewModel.searchMovies(query)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {

                    Surface(
                        modifier = Modifier.padding(start = 12.dp),
                        shape = MaterialTheme.shapes.small
                    ) {
                        IconButton(
                            onClick = onNavegationIconClick,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                                contentDescription = "Back",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                },
                title = {

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Column {
                OutlinedTextField(
                    value = query,
                    onValueChange = { newValue ->
                        query = newValue
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text(stringResource(Res.string.movies_search_input)) }
                )

                when (movieListState) {
                    is MoviesSearchViewModel.MoviesSearchStates.Loading -> {
                        if (query.isNotBlank()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    is MoviesSearchViewModel.MoviesSearchStates.Success -> {
                        val success =
                            movieListState as MoviesSearchViewModel.MoviesSearchStates.Success
                        MoviesSearchSection(
                            title = stringResource(Res.string.movies_search_result),
                            movies = success.movies.movies,
                            onMoviePosterClick = onMovieClick,
                        )
                    }

                    is MoviesSearchViewModel.MoviesSearchStates.Error -> {

                        Text(
                            text = stringResource(Res.string.movies_search),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
