package com.example.cartestapplication.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartestapplication.ui.components.BottomNavigation
import com.example.cartestapplication.ui.screens.home.HomeScreen
import com.example.cartestapplication.ui.screens.map.MapScreen
import com.example.cartestapplication.ui.screens.settings.SettingsScreen
import com.example.cartestapplication.ui.screens.support.SupportScreen
import com.example.cartestapplication.ui.screens.vehicle.VehicleScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel(),
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigation(
                bottomNavItems = mainViewModel.bottomNavMenu,
                navController = navController
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                modifier = Modifier.padding(it),
                startDestination = BottomItem.Home.route
            ) {
                composable(BottomItem.Home.route) {
                    HomeScreen()
                }
                composable(BottomItem.Vehicle.route) {
                    VehicleScreen()
                }
                composable(BottomItem.Map.route) {
                    MapScreen()
                }
                composable(BottomItem.Support.route) {
                    SupportScreen()
                }
                composable(BottomItem.Settings.route) {
                    SettingsScreen()
                }
            }
        }
    }
}