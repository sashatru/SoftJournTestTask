package com.example.eventapp.data.network

import com.example.eventapp.data.model.remote.GraphQLRequest
import com.example.eventapp.data.model.remote.ResponseWrapper
import com.example.eventapp.domain.model.Event
import com.example.eventapp.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import timber.log.Timber

class ApiService(private val client: HttpClient) : IApiService {
    override suspend fun getPublicEvents(page: Int, pageSize: Int): List<Event> {
        Timber.tag("OkHttp").d("AS getPublicEvents page = $page")
        val response = client.post<ResponseWrapper> {
            url(Constants.BASE_URL)
            contentType(ContentType.Application.Json)
            body = GraphQLRequest(
                query = """
                    query {
                        getPublicEvents(options: { take: $pageSize, skip: ${(page - 1) * pageSize} }) {
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
                                    id
                                    sizes {
                                        small {
                                            location
                                        }
                                    }
                                }
                                 performances {
                                    title
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
}
