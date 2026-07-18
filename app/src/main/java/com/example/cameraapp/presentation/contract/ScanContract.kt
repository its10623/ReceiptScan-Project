package com.example.cameraapp.presentation.contract

import com.example.cameraapp.domain.model.Receipt
import com.example.cameraapp.presentation.base.UiIntent
import com.example.cameraapp.presentation.base.UiSideEffect
import com.example.cameraapp.presentation.base.UiState
import java.io.File

object ScanContract {
    data class State(
        val steps: List<ScanStepUi> =
            ScanStep.entries.map {
                ScanStepUi(it, StepStatus.PENDING)
            },
        val isLoading: Boolean = false,
        val receipt: Receipt? = null,
    ) : UiState

    data class ScanStepUi(
        val step: ScanStep,
        val status: StepStatus,
    )

    enum class ScanStep(val label: String) {
        TEXT_RECOGNITION("글자 인식"),
        AMOUNT_EXTRACTION("금액·항목 추출"),
        CATEGORY_CLASSIFICATION("카테고리 자동 분류"),
    }

    enum class StepStatus {
        PENDING,
        IN_PROGRESS,
        DONE,
    }

    sealed class Intent : UiIntent {
        data class StartScan(val file: File) : Intent()
    }

    sealed class SideEffect : UiSideEffect {
        data class NavigateToResult(val receipt: Receipt) : SideEffect()

        data class ShowError(val message: String) : SideEffect()
    }
}
