package com.example.eventapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.eventapp.ui.navigation.NavGraph
import com.example.eventapp.ui.theme.EventAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}