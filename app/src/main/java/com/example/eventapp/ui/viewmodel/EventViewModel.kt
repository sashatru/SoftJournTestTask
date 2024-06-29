package com.example.eventapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventapp.model.data.Event
import com.example.eventapp.repository.IEventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventViewModel(private val repository: IEventRepository) : ViewModel(), IEventViewModel {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> get() = _events

    init {
        fetchEvents()
    }

    override fun fetchEvents() {
        viewModelScope.launch {
            try {
                val events = withContext(Dispatchers.IO) {
                    repository.getEvents()
                }
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
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("BugFix", "fetchEvents error" + e.message)
            }
        }
    }
}
