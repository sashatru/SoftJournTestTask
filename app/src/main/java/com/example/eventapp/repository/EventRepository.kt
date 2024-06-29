package com.example.eventapp.repository

import com.example.eventapp.network.IApiService

class EventRepository(private val apiService: IApiService) : IEventRepository {
    override suspend fun getEvents() = apiService.getPublicEvents()
}