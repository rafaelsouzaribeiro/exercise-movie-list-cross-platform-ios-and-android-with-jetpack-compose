package com.example.move.domain.model

data class CastMember(
    val id: Int,
    val name: String,
    val mainRole: String,
    val character: String,
    val profileUrl: String?
)

val castMember1 = CastMember(
    id = 1,
    name = "Leonardo DiCaprio",
    mainRole = "Actor",
    character = "Jack Dawson",
    profileUrl = "https://image.tmdb.org/t/p/w154/wo2hJpn04vbtmh0B9utCFdsQhx.jpg"
)

val castMenber2 = CastMember(
    id = 2,
    name = "Kate Winslet",
    mainRole = "Actress",
    character = "Rose DeWitt Bukater",
    profileUrl = "https://image.tmdb.org/t/p/w154/6aUWe0GSl69wMTSWWexsorMIvw.jpg"
)