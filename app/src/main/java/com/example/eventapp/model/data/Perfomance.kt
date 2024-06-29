package com.example.eventapp.model.data

import kotlinx.serialization.Serializable

@Serializable
data class Performance(
    val title: String?,
    val startDate: Long?,
    val endDate: Long?,
    val timezone: String?
)
