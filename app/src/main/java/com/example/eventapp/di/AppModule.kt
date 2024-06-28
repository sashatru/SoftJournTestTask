package com.example.eventapp.di

import com.example.eventapp.network.ApiService
import com.example.eventapp.repository.EventRepository
import org.koin.dsl.module

val appModule = module {
    single { ApiService.create() }
    single { EventRepository(get()) }
}
