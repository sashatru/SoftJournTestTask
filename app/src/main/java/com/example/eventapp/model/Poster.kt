package com.example.eventapp.model

data class Poster(
    val id: String,
    val sizes: PosterSize
)

data class PosterSize(
    val small: PosterLocation
)

data class PosterLocation(
    val location: String
)
