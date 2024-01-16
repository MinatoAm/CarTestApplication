package com.example.cartestapplication.ui.screens.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cartestapplication.R
import com.example.cartestapplication.domain.models.CarInfo
import com.example.cartestapplication.domain.models.CarSettingsMenu
import com.example.cartestapplication.domain.models.WeatherModel
import com.example.cartestapplication.ui.components.Dialog
import com.example.cartestapplication.ui.components.SnackBar
import com.example.cartestapplication.ui.components.TopAppBar
import com.example.cartestapplication.ui.theme.CarTestApplicationTheme
import com.example.cartestapplication.ui.theme.darkGrey
import com.example.cartestapplication.ui.theme.primaryBlack
import com.example.cartestapplication.ui.theme.secondary
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@NonRestartableComposable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeScreenViewModel = viewModel(),
) {
    val homeState: HomeScreenState by homeViewModel.state.collectAsState()
    var showSnackBar by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = homeState.snackBarData, block = {
        showSnackBar = !homeState.snackBarData?.snackBarMessage.isNullOrBlank()

        delay(5000)

        showSnackBar = false
//        homeViewModel.resetState()
    })

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = "My ${homeState.carInfo.brand} ${homeState.carInfo.model}",
            )
        },
        snackbarHost = {
            val snackBarData = homeState.snackBarData
            if (showSnackBar) {
                SnackBar(snackBarData = snackBarData)
            }
        }) {
        Box(Modifier.fillMaxSize()) {
            BgImage()

            HomeScreen(
                carInfo = homeState.carInfo,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                weatherTemperature = homeState.weatherData,
                carMenu = homeState.carMenu,
                startProgressFor = homeState.startProgressFor,
                disabledMenus = homeState.disabledMenus,
                onCarMenuClick = { item ->
                    homeViewModel.selectCarMenu(item)
                },
                onProgressFinish = {
                    homeViewModel.setStateUnlocked()
                }
            )
        }

        if (homeState.showDialog) {
            Dialog(
                onConfirm = {
                    homeViewModel.unlockTheCar()
                },
                onDismiss = {
                    homeViewModel.resetState()
                }
            )
        }
    }
}

@Composable
@NonRestartableComposable
private fun HomeScreen(
    carInfo: CarInfo,
    modifier: Modifier = Modifier,
    weatherTemperature: WeatherModel? = null,
    carMenu: List<CarSettingsMenu> = emptyList(),
    startProgressFor: CarSettingsMenu? = null,
    disabledMenus: List<CarSettingsMenu> = emptyList(),
    onCarMenuClick: (CarSettingsMenu) -> Unit = {},
    onProgressFinish: (CarSettingsMenu) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CarAndWeatherInfoScreen(
            carInfo = carInfo,
            weatherTemperature = weatherTemperature,
        )

        CarMenu(
            modifier = modifier
                .padding(top = 56.dp)
                .background(MaterialTheme.colorScheme.background),
            menu = carMenu,
            disabledMenus = disabledMenus,
            startProgressFor = startProgressFor,
            onCarMenuClick = onCarMenuClick,
            onProgressFinish = onProgressFinish
        )
    }
}

@Composable
@NonRestartableComposable
private fun CarMenu(
    menu: List<CarSettingsMenu>,
    modifier: Modifier = Modifier,
    startProgressFor: CarSettingsMenu? = null,
    disabledMenus: List<CarSettingsMenu> = emptyList(),
    onCarMenuClick: (CarSettingsMenu) -> Unit,
    onProgressFinish: (CarSettingsMenu) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(darkGrey)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        menu.forEach {
            val startProgress = startProgressFor == it
            CircleCarControlItem(
                data = it,
                isEnabled = !disabledMenus.contains(it),
                startProgress = startProgress,
                onClick = onCarMenuClick,
                modifier = Modifier.weight(1f),
                onProgressFinish = onProgressFinish
            )
        }
    }
}

@Composable
@NonRestartableComposable
private fun CarAndWeatherInfoScreen(
    carInfo: CarInfo,
    modifier: Modifier = Modifier,
    weatherTemperature: WeatherModel? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = carInfo.image),
            contentDescription = "car",
            modifier = Modifier.offset(106.dp, (46).dp),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.range),
                color = secondary,
                fontFamily = FontFamily(Font(R.font.roboto_regular))
            )

            SpannableTextForMiles()

            WeatherInfo(weatherData = weatherTemperature)
        }
    }
}

@Composable
@NonRestartableComposable
private fun WeatherInfo(
    modifier: Modifier = Modifier,
    weatherData: WeatherModel? = null,
) {
    Row(
        modifier = modifier.padding(top = 8.dp)
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(R.drawable.ic_weather),
            contentDescription = "Image",
            tint = primaryBlack
        )
        Text(
            modifier = Modifier.padding(2.dp, 1.dp, 0.dp, 0.dp),
            fontWeight = FontWeight.Bold,
            text = "${weatherData?.weatherTemperature}° • ${weatherData?.locationName}",
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontSize = 14.sp,
            color = primaryBlack
        )
    }
}

@Composable
private fun SpannableTextForMiles() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.nissan_brand_bold)),
                color = primaryBlack
            )
        ) {
            append("120")
        }
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.nissan_brand_regular)),
                color = primaryBlack
            )
        ) {
            append(" mi")
        }
    }
    Text(text = annotatedString)
}

@Composable
private fun CircleCarControlItem(
    data: CarSettingsMenu,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    loadingTimeSec: Int = 5,
    startProgress: Boolean = false,
    onProgressFinish: (CarSettingsMenu) -> Unit = {},
    onClick: (CarSettingsMenu) -> Unit = {},
) {
    val color =
        if (startProgress) MaterialTheme.colorScheme.primary else primaryBlack

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val alpha = if (isEnabled) 1f else 0.4f
        Box(
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .clickable(enabled = isEnabled) {
                    if (isEnabled) {
                        onClick(data)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            val currentProgress = remember(startProgress) {
                Animatable(if (startProgress) 0f else 1f)
            }
            CircularProgressIndicator(
                progress = currentProgress.value,
                modifier = Modifier
                    .size(60.dp)
                    .alpha(alpha = if (isEnabled || startProgress) 1f else 0.4f),
                strokeWidth = 3.dp,
                color = color
            )

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .alpha(alpha = alpha),
                imageVector = ImageVector.vectorResource(data.resId),
                contentDescription = "Icon",
                tint = primaryBlack
            )

            LaunchedEffect(key1 = startProgress, block = {
                if (startProgress) {
                    currentProgress.animateTo(
                        1f,
                        tween(loadingTimeSec * 1000, easing = LinearEasing)
                    ) {
                        if (this.value == 1f) {
                            onProgressFinish(data)
                        }
                    }
                }
            })
        }

        Text(
            text = stringResource(id = data.title),
            fontSize = 12.sp,
            modifier = Modifier,
            color = primaryBlack,
            maxLines = 1
        )
    }
}

@Composable
@NonRestartableComposable
fun BgImage() {
    Image(
        painter = painterResource(R.drawable.morning),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CarTestApplicationTheme {
        HomeScreen()
    }
}