package com.example.cartestapplication.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.cartestapplication.R
import com.example.cartestapplication.domain.models.CarSettingsMenu
import com.example.cartestapplication.domain.models.SnackBarData
import com.example.cartestapplication.domain.models.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel: ViewModel() {
    private val carMenu: List<CarSettingsMenu> = listOf(
        CarSettingsMenu(
            title = R.string.lock,
            resId = R.drawable.ic_lock,
        ),
        CarSettingsMenu(
            title = R.string.unlock,
            resId = R.drawable.ic_unlock
        ),
        CarSettingsMenu(
            title = R.string.climate,
            resId = R.drawable.ic_climate
        ),
        CarSettingsMenu(
            title = R.string.charge,
            resId = R.drawable.ic_charge
        ),
        CarSettingsMenu(
            title = R.string.lights,
            resId = R.drawable.ic_lights
        )
    )

    private val unlockMenu by lazy { carMenu[1] }

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState(
            carMenu = carMenu,
            disabledMenus = carMenu.take(1),
            weatherData = WeatherModel(
                locationName = "Yerevan",
                weatherTemperature = 70,
                range = 120
            )
        )
    )

    internal val state: StateFlow<HomeScreenState>
        get() = _state.asStateFlow()

    fun selectCarMenu(menu: CarSettingsMenu) {
        if (menu == unlockMenu) {
            updateUIState(showDialog = true)
        } else {
            if (menu == carMenu.first()) {
                updateUIState(disabledMenus = carMenu.take(1))
            }
        }
    }

    fun unlockTheCar() {
        val model = _state.value.carInfo.model
        val message = "Waking $model to Unlock"
        updateUIState(
            disabledMenus = listOf(unlockMenu, carMenu.first()),
            startProgressFor = unlockMenu,
            snackBarMessage = SnackBarData(snackBarMessage = message)
        )
    }


    fun setStateUnlocked() {
        val model: String = _state.value.carInfo.model
        val message = "$model Unlocked"
        updateUIState(
            disabledMenus = listOf(unlockMenu),
            snackBarMessage = SnackBarData(
                snackBarMessage = message,
                isSuccess = true
            )
        )
    }

    private fun updateUIState(
        startProgressFor: CarSettingsMenu? = null,
        disabledMenus: List<CarSettingsMenu> = _state.value.disabledMenus,
        snackBarMessage: SnackBarData? = null,
        showDialog: Boolean = false,
        errorMessage: String? = null,
    ) {
        _state.update {
            it.copy(
                startProgressFor = startProgressFor,
                disabledMenus = disabledMenus,
                snackBarData = snackBarMessage,
                showDialog = showDialog,
                errorMessage = errorMessage,
            )
        }
    }


    fun resetState() {
        updateUIState(showDialog = false)
    }
}