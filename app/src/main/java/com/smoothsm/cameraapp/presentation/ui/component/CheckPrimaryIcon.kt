package com.smoothsm.cameraapp.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.smoothsm.cameraapp.presentation.ui.theme.BgApp
import com.smoothsm.cameraapp.presentation.ui.theme.Primary
import com.smoothsm.cameraapp.presentation.ui.theme.Shape

@Composable
fun CheckPrimaryIcon(
    modifier: Modifier,
    iconModifier: Modifier,
    status: Boolean = false,
) {
    AnimatedVisibility(
        visible = status,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier =
                modifier
                    .clip(Shape.Chip)
                    .background(Primary),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "완료",
                tint = BgApp,
                modifier = iconModifier,
            )
        }
    }
}
