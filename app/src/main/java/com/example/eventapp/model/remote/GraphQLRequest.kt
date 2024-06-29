package com.example.eventapp.model.remote

import com.example.eventapp.model.data.Event
import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    val query: String
)


