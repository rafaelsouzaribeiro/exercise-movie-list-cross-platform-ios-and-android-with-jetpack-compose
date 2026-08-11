package com.example.move.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.move.domain.model.Move1
import com.example.move.domain.model.MovieSection
import com.example.move.ui.components.CastMemberItem
import com.example.move.ui.components.MovieGenreChip
import com.example.move.ui.components.MovieInfoItem
import com.example.move.ui.components.MoviePoster
import com.example.move.ui.moviedetail.MovieDetailViewModel
import com.example.move.ui.moviedetail.MovieDetialScreen
import com.example.move.ui.movies.MovieListViewModel
import com.example.move.ui.movies.MoviesListScreen
import com.example.move.ui.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Star

@Composable
@Preview(showBackground = true)
fun MovieListPreview() {
    MoviePoster(
        movie = Move1,
        onMoviePosterClick = {}
    )
}

@Composable
@Preview(showBackground = true)
fun MovieListScreenPreview() {
    MoviesAppTheme {
        MoviesListScreen(
            movieListState = listOf(MovieListViewModel.MoviesListStates.Success(
                movieSections =
                    MovieSection(
                        sectionType = MovieSection.SectionType.POPULAR,
                        movies = List(10) {
                            Move1
                        }
                    )

                )
            ),
            onMovieClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MovieDetailScreenPreview() {
    MoviesAppTheme {
        MovieDetialScreen(
            movieDetailState = MovieDetailViewModel.MovieDetailState.Success(
                movieDetail = Move1,
            ),
            onNavegationIconClick = { }
        )
    }

}
@Composable
@Preview(showBackground = true)
fun MovieInfoItemPreview() {
    MoviesAppTheme {
        MovieInfoItem(
            icon = FontAwesomeIcons.Solid.Star,
            text = "8.5"
        )
    }

}
@Composable
@Preview(showBackground = true)
fun MovieMovieGenreChipPreview() {
    MoviesAppTheme {
        MovieGenreChip(
            genre = "Action"
        )
    }

}
@Composable
@Preview(showBackground = true)
fun MovieCastMemberItemPreview() {
    MoviesAppTheme {
        CastMemberItem(
            profilePictureUrl = "https://example.com/profile.jpg",
            name = "John Doe",
            character = "Hero"
        )
    }

}