package com.example.eventapp.di

import com.example.eventapp.network.ApiService
import com.example.eventapp.network.HttpClientProvider
import com.example.eventapp.network.IApiService
import com.example.eventapp.repository.EventRepository
import com.example.eventapp.repository.IEventRepository
import com.example.eventapp.ui.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HttpClientProvider.create() }
    single<IApiService> { ApiService(get()) }
    single<IEventRepository> { EventRepository(get()) }
    viewModel { EventViewModel(get()) }
}
