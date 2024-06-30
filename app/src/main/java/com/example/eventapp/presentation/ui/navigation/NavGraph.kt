package com.example.eventapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventapp.presentation.ui.screens.EventListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "eventList") {
        composable("eventList") { EventListScreen() }
    }
}
