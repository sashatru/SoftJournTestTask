package com.example.eventapp.network

import com.example.eventapp.model.data.Event
import com.example.eventapp.model.remote.GraphQLRequest
import com.example.eventapp.model.remote.ResponseWrapper
import com.example.eventapp.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiService(private val client: HttpClient) : IApiService {
    override suspend fun getPublicEvents(): List<Event> {
        val response = client.post<ResponseWrapper> {
            url(Constants.BASE_URL)
            contentType(ContentType.Application.Json)
            body = GraphQLRequest(
                query = Constants.EVENTS_QUERY.trimIndent()
            )
        }
        return response.data.getPublicEvents.items
    }
}