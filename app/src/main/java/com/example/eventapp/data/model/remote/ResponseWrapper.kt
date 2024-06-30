package com.example.eventapp.data.model.remote

import com.example.eventapp.domain.model.Event
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