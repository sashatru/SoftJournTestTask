package com.example.eventapp.repository

import com.example.eventapp.model.data.Event

interface IEventRepository {
    suspend fun getEvents(): List<Event>
}