package com.ajinkya.santoshdrivingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajinkya.santoshdrivingtracker.data.DrivingDatabase
import com.ajinkya.santoshdrivingtracker.ui.AddEntryScreen
import com.ajinkya.santoshdrivingtracker.ui.DashboardScreen
import com.ajinkya.santoshdrivingtracker.ui.DrivingViewModel
import com.ajinkya.santoshdrivingtracker.ui.DrivingViewModelFactory
import com.ajinkya.santoshdrivingtracker.ui.theme.SantoshDrivingTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Initialize Database and Dao
        val database = DrivingDatabase.getDatabase(applicationContext)
        val dao = database.drivingDao()
        
        setContent {
            SantoshDrivingTrackerTheme {
                val navController = rememberNavController()
                val viewModel: DrivingViewModel = viewModel(
                    factory = DrivingViewModelFactory(dao)
                )

                NavHost(navController = navController, startDestination = "dashboard") {
                    composable("dashboard") {
                        DashboardScreen(
                            viewModel = viewModel,
                            onNavigateToAddEntry = { navController.navigate("add_entry") }
                        )
                    }
                    composable("add_entry") {
                        AddEntryScreen(
                            viewModel = viewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}