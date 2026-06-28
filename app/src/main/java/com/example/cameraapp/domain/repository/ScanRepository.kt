package com.example.cameraapp.domain.repository

import com.example.cameraapp.domain.model.Receipt
import java.io.File

interface ScanRepository {
    suspend fun scanReceipt(file: File): Receipt
}
