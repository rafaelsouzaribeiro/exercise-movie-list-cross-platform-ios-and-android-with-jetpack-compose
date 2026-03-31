package com.example.move.data.mapper

import com.example.move.data.network.model.GenreResponse
import com.example.move.domain.model.Genre

fun GenreResponse.toModel() = Genre(
    id = this.id,
    name = this.name
)