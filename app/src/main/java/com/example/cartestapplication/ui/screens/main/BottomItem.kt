package com.example.cartestapplication.ui.screens.main

import androidx.annotation.StringRes
import com.example.cartestapplication.R

sealed class BottomItem(@StringRes val titleResId: Int, val resId: Int, val route: String) {
    data object Home : BottomItem(R.string.bottom_nav_home, R.drawable.bottom_nav_home, "home")
    data object Vehicle : BottomItem(R.string.bottom_nav_vehicle, R.drawable.bottom_nav_vehicle, "vehicle")
    data object Map : BottomItem(R.string.bottom_nav_map, R.drawable.bottom_nav_map, "map")
    data object Support : BottomItem(R.string.bottom_nav_support, R.drawable.bottom_nav_support, "support")
    data object Settings : BottomItem(R.string.bottom_nav_settings, R.drawable.bottom_nav_settings, "settings")
}