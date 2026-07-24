package com.smoothsm.cameraapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.smoothsm.cameraapp.presentation.contract.ScanContract.StepStatus
import com.smoothsm.cameraapp.presentation.ui.theme.BgApp
import com.smoothsm.cameraapp.presentation.ui.theme.Disabled
import com.smoothsm.cameraapp.presentation.ui.theme.Primary
import com.smoothsm.cameraapp.presentation.ui.theme.Shape

@Composable
fun CircularIndicator(status: StepStatus) {
    if (status == StepStatus.PENDING) {
        Box(
            modifier =
                Modifier
                    .size(20.dp)
                    .clip(Shape.Chip)
                    .background(BgApp)
                    .border(width = 4.dp, Disabled, Shape.Chip),
        )
    }

    if (status == StepStatus.IN_PROGRESS) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            color = Primary,
            trackColor = Disabled,
        )
    }

    CheckPrimaryIcon(modifier = Modifier.size(20.dp), iconModifier = Modifier.size(16.dp), status = status == StepStatus.DONE)
}
