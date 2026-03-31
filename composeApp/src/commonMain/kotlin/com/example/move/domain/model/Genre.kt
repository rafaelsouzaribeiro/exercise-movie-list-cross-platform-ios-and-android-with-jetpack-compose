package com.example.move.domain.model

data class Genre(
    val id : Int,
    val name : String
)


val genre1 = Genre(
    id = 28,
    name = "Action"
)

val genre2 = Genre(
    id = 12,
    name = "Adventure"
)