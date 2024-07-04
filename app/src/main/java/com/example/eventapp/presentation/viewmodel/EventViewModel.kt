package com.example.eventapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventapp.domain.model.Event
import com.example.eventapp.domain.usecase.IGetEventUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class EventViewModel(private val getEventUseCase: IGetEventUseCase) : ViewModel(), IEventViewModel {
    private var downloadingEventsFinished: Boolean = false
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> get() = _events

    private var currentPage = 1
    private val pageSize = 10

    init {
        fetchEvents()
    }

    override fun fetchEvents() {
        Timber.tag("OkHttp")
            .d("EVM fetchEvents downloadingEventsFinished = $downloadingEventsFinished")
        if (!downloadingEventsFinished) viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    getEventUseCase.execute(currentPage, pageSize)
                }
            }.onSuccess { newEvents ->
                Timber.tag("OkHttp").wtf("EVM newEvents.isEmpty() = ${newEvents.isEmpty()} ")
                // Process data using sequences: filter, sort, and map
                val processedEvents = newEvents.asSequence()
                    .filter { it.priceRangeStart != null && it.priceRangeStart > 0 } // Example filter: price greater than 0
                    .sortedBy { it.title } // Example sort: by title
                    .map { event ->
                        // Example map: convert price to a formatted string
                        event.copy(title = "${event.title} - ${event.priceRangeStart} ${event.currencyKey}")
                    }
                    .toList()

                // Append new events to the existing list
                if (newEvents.isNotEmpty()) {
                    _events.value += processedEvents

                    // Increment page for next fetch
                    currentPage++
                } else downloadingEventsFinished = true
            }.onFailure { e ->
                e.printStackTrace()
                Timber.tag("OkHttp").e("fetchEvents error %s", e.message)
            }
        }
    }
}
