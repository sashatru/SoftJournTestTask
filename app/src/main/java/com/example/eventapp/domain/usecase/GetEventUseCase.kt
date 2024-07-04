package com.example.eventapp.domain.usecase

import com.example.eventapp.data.repository.IEventRepository
import com.example.eventapp.domain.model.Event

class GetEventUseCase(private val repository: IEventRepository) : IGetEventUseCase {
    override suspend fun execute(page: Int, pageSize: Int): List<Event> {
        return repository.getEvents(page, pageSize)
    }
}
