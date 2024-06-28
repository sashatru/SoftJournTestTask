package com.example.eventapp.repository

import com.example.eventapp.network.ApiService

class EventRepository(private val apiService: ApiService) {
    suspend fun getEvents() = apiService.getPublicEvents()
}