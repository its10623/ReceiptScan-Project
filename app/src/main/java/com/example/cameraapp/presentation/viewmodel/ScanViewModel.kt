package com.example.cameraapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.cameraapp.domain.usecase.ScanUseCase
import com.example.cameraapp.presentation.base.BaseViewModel
import com.example.cameraapp.presentation.contract.ScanContract
import com.example.cameraapp.presentation.contract.ScanContract.ScanStep.TEXT_RECOGNITION
import com.example.cameraapp.presentation.contract.ScanContract.ScanStep.AMOUNT_EXTRACTION
import com.example.cameraapp.presentation.contract.ScanContract.ScanStep.CATEGORY_CLASSIFICATION
import com.example.cameraapp.presentation.contract.ScanContract.StepStatus.IN_PROGRESS
import com.example.cameraapp.presentation.contract.ScanContract.StepStatus.DONE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val scanUseCase: ScanUseCase
) :
    BaseViewModel<ScanContract.Event, ScanContract.State, ScanContract.Effect>() {
    override fun createInitialState() = ScanContract.State()
    override fun handleEvent(event: ScanContract.Event) {
        when (event) {
            is ScanContract.Event.StartScan ->  {
                scanReceipt(event.file)
            }
        }
    }

    private fun scanReceipt(file: File) {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            try {
                updateStep(TEXT_RECOGNITION, IN_PROGRESS)
                delay(5000.milliseconds)
                updateStep(TEXT_RECOGNITION, DONE)

                updateStep(AMOUNT_EXTRACTION, IN_PROGRESS)
                delay(5000.milliseconds)
                updateStep(AMOUNT_EXTRACTION, DONE)

                updateStep(CATEGORY_CLASSIFICATION, IN_PROGRESS)
                // TODO: 서버 연동 시 아래 주석 해제 후 가짜 데이터 제거
                // val receipt = scanUseCase.scanReceipt(file)
                delay(3000.milliseconds)
                val receipt = com.example.cameraapp.domain.model.Receipt(
                    id = 1L,
                    storeName = "테스트 마트",
                    totalPrice = 15000,
                    purchaseAt = "2026.06.28 14:00",
                    category = com.example.cameraapp.domain.model.Category.MEAL,
                    items = listOf(
                        com.example.cameraapp.domain.model.ReceiptItem(id = 1L, name = "사과", price = 5000, quantity = 1),
                        com.example.cameraapp.domain.model.ReceiptItem(id = 2L, name = "우유", price = 10000, quantity = 1),
                    ),
                    createdAt = java.time.LocalDateTime.now(),
                    updatedAt = java.time.LocalDateTime.now(),
                )
                updateStep(CATEGORY_CLASSIFICATION, DONE)

                setState { copy(receipt = receipt) }
                setEffect { ScanContract.Effect.NavigateToResult(receipt) }
                setState { copy(isLoading = false) }
            } catch (e: Exception) {
                setState { copy(isLoading = false) }
                setEffect { ScanContract.Effect.ShowError("${e.message}") }
            }
        }
    }

    private fun updateStep(step: ScanContract.ScanStep, status: ScanContract.StepStatus) {
        setState {
            copy(steps = steps.map { if (it.step == step) it.copy(status = status) else it})
        }
    }
}