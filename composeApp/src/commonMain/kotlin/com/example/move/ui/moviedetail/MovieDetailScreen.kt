package com.example.move.ui.moviedetail

import VideoPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.move.domain.model.Movie
import com.example.move.ui.components.CastMemberItem
import com.example.move.ui.components.MovieGenreChip
import com.example.move.ui.components.MovieInfoItem
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Clock
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Star
import move.composeapp.generated.resources.Res
import move.composeapp.generated.resources.movies_detail
import move.composeapp.generated.resources.movies_detail_watch_trailer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val movieDetailState = viewModel.movieDetailState.collectAsStateWithLifecycle()
    MovieDetialScreen(
        movieDetailState = movieDetailState.value,
        onNavegationIconClick = navigateBack,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetialScreen(
    movieDetailState: MovieDetailViewModel.MovieDetailState,
    onNavegationIconClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.movies_detail)
                    )
                },
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,

                    )
            )
        }
    ) { paddingValues ->
        var youtubeVideoId by remember { mutableStateOf<String?>(null) }

        youtubeVideoId?.let {key->
            ModalBottomSheet(
                onDismissRequest = { youtubeVideoId=null },
                sheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true
                ),
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding()
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    VideoPlayer(
                        modifier = Modifier.fillMaxWidth().aspectRatio(16 / 9f),
                        url = "https://www.youtube.com/watch?v=$key",
                        showControls = true,
                        autoPlay = true
                    )
                }

            }
        }
        Box(
            modifier = Modifier.padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (movieDetailState) {
                is MovieDetailViewModel.MovieDetailState.Loading -> {
                    CircularProgressIndicator()
                }
                is MovieDetailViewModel.MovieDetailState.Success -> {
                    MovieDetailContent(
                        movie = movieDetailState.movieDetail,
                        onWatchTrailerClick = { youtubeVideoId=it }
                        )
                }
                is MovieDetailViewModel.MovieDetailState.Error -> {
                    Text(
                        text = "Error: ${movieDetailState.message}",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDetailContent(
    movie: Movie,
    modifier: Modifier = Modifier,
    onWatchTrailerClick: (key:String) -> Unit
) {
    val scrollState = rememberScrollState()


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = MaterialTheme.shapes.large
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                modifier = Modifier.clip(MaterialTheme.shapes.large)
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movie.title,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovieInfoItem(
                    icon = FontAwesomeIcons.Solid.Star,
                    text = movie.rating
                )
                Spacer(modifier = Modifier.width(16.dp))

                movie.duration?.let {
                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Clock,
                        text = it
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                MovieInfoItem(
                    icon = FontAwesomeIcons.Solid.Calendar,
                    text = "${movie.year}"
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                movie.genres?.forEachIndexed { index, genre ->
                    MovieGenreChip(
                        genre = genre.name
                    )

                    if (index < movie.genres.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            movie.movieTrailerYoutubeKey?.let {
                ElevatedButton(
                    onClick = {
                        onWatchTrailerClick(it)
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Play,
                        contentDescription = "Play",
                        modifier = Modifier.size(12.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = stringResource(Res.string.movies_detail_watch_trailer),
                        modifier = Modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            movie.castMembers?.let {
                Spacer(modifier = Modifier.height(16.dp))
                BoxWithConstraints(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val itemWidth = this.maxWidth * 0.55f

                    LazyRow(
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(it) { member ->
                            CastMemberItem(
                                profilePictureUrl = member.profileUrl,
                                name = member.name,
                                character = member.character,
                                modifier = Modifier.width(itemWidth)
                            )
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall
                )


            }
        }
    }
}

