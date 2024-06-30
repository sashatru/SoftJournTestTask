package com.example.eventapp.data.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    val query: String
)


