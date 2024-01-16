package com.example.cartestapplication.domain.models

import androidx.annotation.DrawableRes
import com.example.cartestapplication.R

data class CarInfo(
    val brand: String,
    val model: String,
    @DrawableRes val image: Int = R.drawable.car_photo,
)