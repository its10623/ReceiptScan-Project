package com.smoothsm.cameraapp.data.repository

import com.smoothsm.cameraapp.data.network.api.ReceiptApi
import com.smoothsm.cameraapp.data.network.model.toDomain
import com.smoothsm.cameraapp.domain.model.Receipt
import com.smoothsm.cameraapp.domain.repository.ScanRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ScanRepositoryImpl
    @Inject
    constructor(
        private val receiptApi: ReceiptApi,
    ) : ScanRepository {
        override suspend fun scanReceipt(file: File): Receipt {
            val parseFile = fileToMultipartBody(file)
            val response = receiptApi.scanReceipt(parseFile)
            val receipt = response.body()?.receipt ?: throw Exception("스캔 실패")
            return receipt.toDomain()
        }

        private fun fileToMultipartBody(
            file: File,
            key: String = "file",
        ): MultipartBody.Part {
            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

            return MultipartBody.Part.createFormData(key, file.name, requestBody)
        }
    }
