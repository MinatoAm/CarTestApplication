package com.example.cartestapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cartestapplication.R
import com.example.cartestapplication.ui.theme.primaryBlack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@NonRestartableComposable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_support),
                contentDescription = "support",
                modifier = Modifier.size(24.dp),
                tint = primaryBlack
            )
        },
        actions = {
            Icon(
                painter = painterResource(R.drawable.ic_notification),
                contentDescription = "notifications",
                modifier = Modifier.size(24.dp),
                tint = primaryBlack
            )
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = "lock",
                    tint = primaryBlack
                )

                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = primaryBlack,
                    fontFamily = FontFamily(Font(R.font.nissan_brand_regular)),
                )
            }
        })
}
