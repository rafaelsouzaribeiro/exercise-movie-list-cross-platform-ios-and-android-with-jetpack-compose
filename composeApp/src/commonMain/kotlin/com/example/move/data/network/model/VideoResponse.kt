package com.example.move.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoListResponse(
    val id: Int,
    val results: List<VideoResponse>
)
@Serializable
data class VideoResponse(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String,
    val official: Boolean,
)