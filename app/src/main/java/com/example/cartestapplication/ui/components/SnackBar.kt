package com.example.cartestapplication.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.cartestapplication.R
import com.example.cartestapplication.domain.models.SnackBarData
import com.example.cartestapplication.ui.theme.successColor

@Composable
@NonRestartableComposable
fun SnackBar(snackBarData: SnackBarData?) {
    Snackbar(content = {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = snackBarData?.snackBarMessage.orEmpty(),
                modifier = Modifier.weight(1f),
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontSize = 15.sp,
                color = Color.White
            )

            if (snackBarData?.isSuccess == true) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "success",
                    tint = successColor
                )
            }
        }
    })
}
