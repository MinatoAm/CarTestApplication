package com.example.cartestapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cartestapplication.R
import com.example.cartestapplication.ui.screens.main.BottomItem


@Composable
fun BottomNavigation(
    bottomNavItems: List<BottomItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.White
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        bottomNavItems.forEach {
            BottomNavItem(it, currentRoute == it.route, Modifier.weight(1f)) { selected ->
                navController.navigate(selected.route)
            }
        }
    }
}

@Composable
fun BottomNavItem(
    data: BottomItem, isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: (BottomItem) -> Unit = {},
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp)
            .clickable { onClick(data) }) {
        val color =
            if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        val fontFamily =
            if (isSelected) FontFamily(Font(R.font.nissan_brand_bold)) else FontFamily(Font(R.font.nissan_brand_regular))

        Divider(
            modifier = Modifier.height(2.dp),
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
        )

        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = data.resId),
            contentDescription = "image",
            tint = color
        )

        Text(
            text = stringResource(id = data.titleResId),
            fontSize = 12.sp,
            color = color,
            fontFamily = fontFamily
        )
    }
}