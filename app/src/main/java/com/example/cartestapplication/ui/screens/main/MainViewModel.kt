package com.example.cartestapplication.ui.screens.main

import androidx.lifecycle.ViewModel

class MainViewModel :ViewModel() {
    val bottomNavMenu: List<BottomItem> by lazy {
        listOf(
            BottomItem.Home,
            BottomItem.Vehicle,
            BottomItem.Map,
            BottomItem.Support,
            BottomItem.Settings,
        )
    }
}