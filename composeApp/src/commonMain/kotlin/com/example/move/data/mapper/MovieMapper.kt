package com.example.move.data.mapper

import com.example.move.data.network.IMAGE_BASE_URL
import com.example.move.data.network.model.CastMemberResponse
import com.example.move.data.network.model.MovieResponse
import com.example.move.domain.model.ImageSize
import com.example.move.domain.model.Movie
import com.example.move.utils.FormatRating
import kotlin.math.roundToInt

fun MovieResponse.toModel(
    castMemberResponse: List<CastMemberResponse>?=null,
    imageSize: ImageSize = ImageSize.SMALL,
)  = Movie (
     id = this.id,
     title = this.title,
     overview = this.overview,
     posterUrl = "$IMAGE_BASE_URL/${imageSize.size}/${this.posterPath}",
    genres = this.genres?.map { it.toModel() },
    year = this.getYearGenresFromReleaseDate(),
    duration = this.getDurationForHourandMinutes(),
    rating = this.voteAverage.FormatRating(),
    castMembers = castMemberResponse
        ?.filter { it.department=="Acting" }
        ?.take(20)
        ?.map { it.toModel() }


)

private fun MovieResponse.getYearGenresFromReleaseDate():Int{
    return this.releaseDate.year
}

private fun MovieResponse.getDurationForHourandMinutes():String?{
    return  this.runtime?.let {
        val hours = it / 60
        val minutes = it % 60

         buildString {
            if (hours > 0) {
                append("${hours}h ")
            }
            if (minutes > 0) {
                append("${minutes}m")
            }
        }
    }

}