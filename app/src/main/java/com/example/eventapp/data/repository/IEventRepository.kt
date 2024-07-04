package com.example.eventapp.data.repository

import com.example.eventapp.domain.model.Event

interface IEventRepository {
    suspend fun getEvents(page: Int, pageSize: Int): List<Event>
}