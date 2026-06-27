package com.example.cameraapp.domain.repository

import com.example.cameraapp.domain.model.Receipt
import okhttp3.MultipartBody
import retrofit2.http.Multipart

interface ReceiptRepository {
    suspend fun scanReceipt(file: MultipartBody.Part): Receipt
}
