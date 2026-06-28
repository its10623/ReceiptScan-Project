package com.example.cameraapp.domain.usecase

import com.example.cameraapp.domain.model.Receipt
import java.io.File

interface ScanUseCase {
    suspend fun scanReceipt(file: File): Receipt
}
