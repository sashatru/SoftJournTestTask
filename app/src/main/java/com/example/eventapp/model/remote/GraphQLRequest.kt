package com.example.eventapp.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    val query: String
)


