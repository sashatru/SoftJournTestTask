package com.example.eventapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Poster(
    val id: String?,
    val sizes: PosterSize?
)

@Serializable
data class PosterSize(
    val small: PosterLocation?
)

@Serializable
data class PosterLocation(
    val location: String?
)
