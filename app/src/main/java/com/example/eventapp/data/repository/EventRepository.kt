package com.example.eventapp.data.repository

import com.example.eventapp.data.network.IApiService

class EventRepository(private val apiService: IApiService) : IEventRepository {
    override suspend fun getEvents() = apiService.getPublicEvents()
}