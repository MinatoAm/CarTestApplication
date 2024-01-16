package com.example.cartestapplication.ui.screens.home

import com.example.cartestapplication.domain.models.CarInfo
import com.example.cartestapplication.domain.models.CarSettingsMenu
import com.example.cartestapplication.domain.models.SnackBarData
import com.example.cartestapplication.domain.models.WeatherModel

data class HomeScreenState(
    val carInfo: CarInfo = CarInfo("Nissan", "Arya"),
    val carMenu: List<CarSettingsMenu> = emptyList(),
    val startProgressFor: CarSettingsMenu? = null,
    val disabledMenus: List<CarSettingsMenu> = emptyList(),
    val weatherData: WeatherModel? = null,
    val showDialog: Boolean = false,
    val snackBarData: SnackBarData? = null,
    val errorMessage: String? = null,
)
