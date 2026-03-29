package com.example.move.ui.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.move.domain.model.Move1
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
import move.composeapp.generated.resources.foto
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieDetailRoute(){
    MovieDetialScreen(movie = Move1)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetialScreen(movie: Movie) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Movie Details")
                },
                navigationIcon = {

                    Surface(
                        modifier = Modifier.padding(start = 12.dp),
                        shape = MaterialTheme.shapes.small
                    ) {
                        IconButton(
                            onClick = { /* TODO: Handle back navigation */ },
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
        Column(
            modifier = Modifier.padding(paddingValues)
                .fillMaxSize()
        ) {
            Surface(
                modifier = Modifier.fillMaxSize()
                    .weight(1f)
                    .padding(16.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Image(
                    painter = painterResource(Res.drawable.foto),
                    contentDescription = null,
                    modifier = Modifier.clip(MaterialTheme.shapes.large),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .weight(2f)
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
                        text = "7.5"
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Clock,
                        text = "7h50m"
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Calendar,
                        text = "2022"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MovieGenreChip(
                        genre = "Action"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                ElevatedButton(
                    onClick = { /* TODO: Handle watch now action */ },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Play,
                        contentDescription = "Play",
                        modifier = Modifier.size(12.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "Watch Now",
                        modifier = Modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                BoxWithConstraints(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val itemWitdh = this.maxWidth * 0.55f

                    LazyRow(
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(10) {
                            CastMemberItem(
                                profilePictureUrl = "",
                                name = "Actor Name",
                                character = "Character Name",
                                modifier = Modifier.width(itemWitdh)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.padding(16.dp)
                ){
                    Text(
                        text= "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                        style = MaterialTheme.typography.bodySmall
                        )

                }


            }
        }
    }
}