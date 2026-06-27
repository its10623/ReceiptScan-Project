package com.example.cameraapp.data.repository

import android.util.Log
import com.example.cameraapp.data.network.api.ReceiptApi
import com.example.cameraapp.data.network.model.toDomain
import com.example.cameraapp.domain.model.Receipt
import com.example.cameraapp.domain.repository.ReceiptRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptApi: ReceiptApi
) : ReceiptRepository {
    override suspend fun scanReceipt(file: MultipartBody.Part): Receipt {
        val response = receiptApi.scanReceipt(file)
        Log.d("ReceiptRepo", "code: ${response.code()}")
        Log.d("ReceiptRepo", "body: ${response.body()}")
        Log.d("ReceiptRepo", "errorBody: ${response.errorBody()?.string()}")
        val receipt = response.body()?.receipt ?: throw Exception("스캔 실패")
        return receipt.toDomain()
    }
}
