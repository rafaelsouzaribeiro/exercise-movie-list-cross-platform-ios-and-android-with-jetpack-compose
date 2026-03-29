package com.example.move.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MovieGenreChip(
    modifier: Modifier = Modifier,
    genre: String
) {
    Surface(
        modifier= Modifier,
        shape = MaterialTheme.shapes.large,
        color = Color.Red
    ) {
        Text(
            text = genre,
            modifier = Modifier
                .padding(horizontal = 8.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )

    }
}