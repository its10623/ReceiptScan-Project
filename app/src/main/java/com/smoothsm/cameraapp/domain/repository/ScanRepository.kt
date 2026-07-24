package com.smoothsm.cameraapp.domain.repository

import com.smoothsm.cameraapp.domain.model.Receipt
import java.io.File

interface ScanRepository {
    suspend fun scanReceipt(file: File): Receipt
}
