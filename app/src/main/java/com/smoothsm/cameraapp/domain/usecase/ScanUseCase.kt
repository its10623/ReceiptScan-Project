package com.smoothsm.cameraapp.domain.usecase

import com.smoothsm.cameraapp.domain.model.Receipt
import java.io.File

interface ScanUseCase {
    suspend fun scanReceipt(file: File): Receipt
}
