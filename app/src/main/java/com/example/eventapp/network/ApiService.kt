package com.example.eventapp.network

import com.example.eventapp.model.Event
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
            url("https://ticketing-service-test2-cluster.cinewav.com/api/graphql")
            contentType(ContentType.Application.Json)
            body = GraphQLRequest(
                query = """
                    query {
                        getPublicEvents(options: { take: 10 }) {
                            items {
                                id
                                title
                                priceRangeStart
                                currencyKey
                                countryKey
                                organizer {
                                    companyName
                                }
                                posters {
                                    sizes {
                                        small {
                                            location
                                        }
                                    }
                                }
                                performances {
                                    startDate
                                    endDate
                                    timezone
                                }
                            }
                        }
                    }
                """.trimIndent()
            )
        }
        return response.data.getPublicEvents.items
    }

    companion object {
        fun create(): ApiService {
            val client = HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
                install(Logging) {
                    level = LogLevel.BODY
                }
            }
            return ApiService(client)
        }
    }
}