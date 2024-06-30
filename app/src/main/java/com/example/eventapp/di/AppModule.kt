package com.example.eventapp.di

import com.example.eventapp.data.network.ApiService
import com.example.eventapp.data.network.HttpClientProvider
import com.example.eventapp.data.network.IApiService
import com.example.eventapp.data.repository.EventRepository
import com.example.eventapp.data.repository.IEventRepository
import com.example.eventapp.domain.usecase.GetEventUseCase
import com.example.eventapp.domain.usecase.IGetEventUseCase
import com.example.eventapp.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HttpClientProvider.create() }
    single<IApiService> { ApiService(get()) }
    single<IEventRepository> { EventRepository(get()) }
    single<IGetEventUseCase> { GetEventUseCase(get()) }
    viewModel { EventViewModel(get()) }
}
