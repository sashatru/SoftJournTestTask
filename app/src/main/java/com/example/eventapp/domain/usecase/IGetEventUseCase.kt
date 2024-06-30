package com.example.eventapp.domain.usecase

import com.example.eventapp.domain.model.Event

interface IGetEventUseCase {
    suspend fun execute(): List<Event>
}