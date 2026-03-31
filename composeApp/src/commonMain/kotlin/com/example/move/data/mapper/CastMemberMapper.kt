package com.example.move.data.mapper

import com.example.move.data.network.IMAGE_SMALL_BASE_URL
import com.example.move.data.network.model.CastMemberResponse
import com.example.move.domain.model.CastMember

fun CastMemberResponse.toModel()  = CastMember(
    id = this.id,
    name = this.name,
    mainRole = this.department,
    character = this.character,
    profileUrl = "${IMAGE_SMALL_BASE_URL}/${this.profilePath}",
)