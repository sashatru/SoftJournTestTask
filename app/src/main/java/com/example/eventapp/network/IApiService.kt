package com.example.eventapp.network

import com.example.eventapp.model.data.Event

interface IApiService {
    suspend fun getPublicEvents(): List<Event>
}