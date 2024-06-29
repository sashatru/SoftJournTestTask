package com.example.eventapp.network

import com.example.eventapp.model.Event
import com.example.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiService(private val client: HttpClient) {
    suspend fun getPublicEvents(): List<Event> {
        val response = client.post<ResponseWrapper> {
            url(Constants.BASE_URL)
            contentType(ContentType.Application.Json)
            body = GraphQLRequest(
                query = Constants.EVENTS_QUERY.trimIndent()
            )
        }
        return response.data.getPublicEvents.items
    }

    companion object {
        fun create(): ApiService {
            val client = HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        coerceInputValues = true
                    })
                }
                install(Logging) {
                    level = LogLevel.BODY
                }
            }
            return ApiService(client)
        }
    }
}