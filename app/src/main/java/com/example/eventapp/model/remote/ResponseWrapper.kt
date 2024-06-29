package com.example.eventapp.model.remote

import com.example.eventapp.model.data.Event
import kotlinx.serialization.Serializable

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