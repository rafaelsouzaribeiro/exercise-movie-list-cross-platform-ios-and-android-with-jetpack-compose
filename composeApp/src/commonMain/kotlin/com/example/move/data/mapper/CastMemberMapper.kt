package com.example.move.data.mapper

import com.example.move.data.network.IMAGE_BASE_URL
import com.example.move.data.network.model.CastMemberResponse
import com.example.move.domain.model.CastMember
import com.example.move.domain.model.ImageSize

fun CastMemberResponse.toModel()  = CastMember(
    id = this.id,
    name = this.name,
    mainRole = this.department,
    character = this.character,
    profileUrl = "${IMAGE_BASE_URL}/${ImageSize.X_SMALL.size}/${this.profilePath}",
)