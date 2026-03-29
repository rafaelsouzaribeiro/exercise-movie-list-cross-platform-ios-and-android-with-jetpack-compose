package com.example.move.ui.moviedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.move.domain.model.Move1
import com.example.move.domain.model.Movie
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft

@Composable
fun MovieDetailRoute(){
    MovieDetialScreen(movie = Move1)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetialScreen(movie: Movie){
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
                                    modifier = Modifier.padding(8.dp))
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
        ) {  }
    }
}