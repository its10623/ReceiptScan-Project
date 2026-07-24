package com.smoothsm.cameraapp.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.smoothsm.cameraapp.R
import com.smoothsm.cameraapp.presentation.contract.ScanContract
import com.smoothsm.cameraapp.presentation.ui.component.CircularIndicator
import com.smoothsm.cameraapp.presentation.ui.theme.BgApp
import com.smoothsm.cameraapp.presentation.ui.theme.Primary
import com.smoothsm.cameraapp.presentation.ui.theme.TextSub
import com.smoothsm.cameraapp.presentation.viewmodel.ScanViewModel

@Preview
@Composable
fun ScanningScreen(viewModel: ScanViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scan_receipt))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    Scaffold(
        modifier =
            Modifier
                .fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(BgApp)
                    .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.weight(0.1f))

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier =
                    Modifier
                        .size(250.dp),
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularIndicator(status = ScanContract.StepStatus.IN_PROGRESS)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "AI가 영수증을 읽고 있어요",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            Text(
                text = "잠시만 기다려 주세요",
                style = MaterialTheme.typography.bodySmall,
                color = TextSub,
            )
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
                state.steps.forEach { stepUi ->
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 12.dp),
                    ) {
                        CircularIndicator(stepUi.status)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stepUi.step.label,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(0.5f))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier =
                        Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Primary),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "평균 15초 안에 분석이 끝나요",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSub,
                )
            }
        }
    }
}
