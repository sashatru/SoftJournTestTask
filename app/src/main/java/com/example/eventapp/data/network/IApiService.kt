package com.example.eventapp.data.network

import com.example.eventapp.domain.model.Event

interface IApiService {
    suspend fun getPublicEvents(): List<Event>
}