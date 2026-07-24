package com.smoothsm.cameraapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme =
    lightColorScheme(
        primary = Primary,
        onPrimary = Surface,
        primaryContainer = PrimarySoft,
        onPrimaryContainer = Ink,
        secondary = PrimaryDeep,
        onSecondary = Surface,
        secondaryContainer = PrimaryTint,
        onSecondaryContainer = Ink,
        background = BgApp,
        onBackground = Ink,
        surface = Surface,
        onSurface = Ink,
        surfaceVariant = BgApp,
        onSurfaceVariant = TextSub,
        outline = Border,
        error = Expense,
        onError = Surface,
    )

@Composable
fun CameraAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content,
    )
}
