package com.example.cameraapp.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cameraapp.presentation.ui.theme.CameraAppTheme
import com.example.cameraapp.presentation.ui.theme.Primary
import com.example.cameraapp.presentation.ui.theme.Surface

@Composable
fun PrimaryButton(
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    text: String? = null
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = Surface
        )
    ) {
        Text(
            text = "로그인",
            modifier = Modifier
                .padding(8.dp),
            style = MaterialTheme.typography.headlineMedium

        )
    }
}

@Composable
@Preview
fun PreviewScreen() {
    CameraAppTheme {
        PrimaryButton()
    }
}