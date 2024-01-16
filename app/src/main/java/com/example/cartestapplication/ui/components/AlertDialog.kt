package com.example.cartestapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.cartestapplication.R
import com.example.cartestapplication.ui.theme.primary
import com.example.cartestapplication.ui.theme.primaryBlack

@Composable
fun Dialog(
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    dialogCornerRadius: Dp = 5.dp,
    buttonCornerRadius: Dp = 4.dp
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            shape = RoundedCornerShape(dialogCornerRadius),
        ) {
            Column(
                modifier = Modifier.background(Color.White),
            ) {
                Text(
                    text = stringResource(R.string.unlock_dialog_title),
                    modifier = Modifier.padding(16.dp),
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 18.sp,
                    color = primaryBlack
                )
                Text(
                    text = stringResource(R.string.unlock_dialog_text),
                    modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp),
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 14.sp,
                    color = primaryBlack
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    DialogButton(
                        txt = stringResource(R.string.unlock_dialog_negative_btn),
                        bgColor = Color.White,
                        textColor = primary,
                        cornerRadius = buttonCornerRadius,
                        onClick = onDismiss
                    )
                    DialogButton(
                        txt = stringResource(R.string.unlock_dialog_positive_btn),
                        bgColor = MaterialTheme.colorScheme.primary,
                        textColor = Color.White,
                        cornerRadius = buttonCornerRadius,
                        onClick = onConfirm,
                    )
                }
            }
        }
    }
}

@Composable
@NonRestartableComposable
private fun DialogButton(
    txt: String,
    bgColor: Color = Color.Transparent,
    textColor: Color = primary,
    cornerRadius: Dp = 3.dp,
    onClick: () -> Unit = {},
) {
    TextButton(
        colors = ButtonDefaults.buttonColors(containerColor = bgColor),
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(cornerRadius)
    )
    {
        Text(
            text = txt,
            color = textColor,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontSize = 14.sp,
            fontWeight = FontWeight(700)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    Dialog()
}