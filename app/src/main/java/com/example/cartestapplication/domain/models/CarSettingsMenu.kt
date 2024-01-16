package com.example.cartestapplication.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CarSettingsMenu(
    @StringRes val title: Int,
    @DrawableRes val resId: Int,
    val enabled: Boolean = true,
)
