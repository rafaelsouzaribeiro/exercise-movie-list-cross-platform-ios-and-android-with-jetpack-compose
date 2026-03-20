package com.example.move.domain.model

import com.example.move.data.network.IMAGE_SMALL_BASE_URL
import com.example.move.data.network.model.MovieResponse

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String)

fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterUrl = "$IMAGE_SMALL_BASE_URL${this.posterPath}"
    )
}
val Move1 = Movie(
    id = 1,
    title = "The Shawshank Redemption",
    overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
    posterUrl = "https://image.tmdb.org/t/p/w500/q6y0c0lT5lTj3vF3r2RFFR4GJ.jpg"
)