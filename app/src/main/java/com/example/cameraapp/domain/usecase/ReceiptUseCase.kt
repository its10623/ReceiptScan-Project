package com.example.cameraapp.domain.usecase

import com.example.cameraapp.domain.model.Receipt
import okhttp3.MultipartBody

interface ReceiptUseCase {
    suspend fun scanReceipt(file: MultipartBody.Part): Receipt
}
