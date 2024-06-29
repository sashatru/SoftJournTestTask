package com.example.eventapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventapp.ui.screens.EventListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "eventList") {
        composable("eventList") { EventListScreen() }
    }
}
