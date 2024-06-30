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
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> get() = _events

    init {
        fetchEvents()
    }

    override fun fetchEvents() {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    getEventUseCase.execute()
                }
            }.onSuccess { events ->
                // Process data using sequences: filter, sort, and map
                val processedEvents = events.asSequence()
                    .filter { it.priceRangeStart!! > 0 } // Example filter: price greater than 0
                    .sortedBy { it.title } // Example sort: by title
                    .map { event ->
                        // Example map: convert price to a formatted string
                        event.copy(title = "${event.title} - ${event.priceRangeStart} ${event.currencyKey}")
                    }
                    .toList()
                _events.value = processedEvents
            }.onFailure { e ->
                e.printStackTrace()
                Timber.tag("BugFix").e("fetchEvents error %s", e.message)
            }
        }
    }
}
