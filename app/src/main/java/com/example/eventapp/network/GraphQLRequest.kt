package com.example.eventapp.network

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

@Serializable
data class Event(
    val id: String,
    val title: String,
    val priceRangeStart: Int,
    val currencyKey: String,
    val countryKey: String,
    val organizer: Organizer,
    val posters: List<Poster>,
    val performances: List<Performance>
)

@Serializable
data class Organizer(
    val companyName: String
)

@Serializable
data class Poster(
    val sizes: Sizes
)

@Serializable
data class Sizes(
    val small: Image
)

@Serializable
data class Image(
    val location: String
)

@Serializable
data class Performance(
    val startDate: Long,
    val endDate: Long,
    val timezone: String
)