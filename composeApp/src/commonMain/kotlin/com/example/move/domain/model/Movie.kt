package com.example.move.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val genres: List<Genre>?,
    val year: Int,
    val duration:String?,
    val rating: String,
    val castMembers: List<CastMember>?
    )


val Move1 = Movie(
    id = 1,
    title = "The Shawshank Redemption",
    overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
    posterUrl = "https://image.tmdb.org/t/p/w500/q6y0c0lT5lTj3vF3r2RFFR4GJ.jpg",
    genres=listOf(genre1,genre2),
    year = 1994,
    duration = "2h 22m",
    rating = "9.3",
    castMembers = listOf(castMember1,castMenber2    )
)