package com.example.eventapp.network

import com.example.eventapp.model.Event
import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    val query: String
)

@Serializable
data class ResponseWrapper(
    val data: DataWrapper
)

@Serializable
data class DataWrapper(
    val getPublicEvents: EventResponse
)
@Serializable
data class EventResponse(
    val items: List<Event>
)
