package com.example.cameraapp.domain.usecase

import com.example.cameraapp.domain.model.Receipt
import com.example.cameraapp.domain.repository.ReceiptRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class ReceiptUseCaseImpl
@Inject
constructor(
        private val receiptRepository: ReceiptRepository
) : ReceiptUseCase {
    override suspend fun scanReceipt(file: MultipartBody.Part): Receipt {
        return receiptRepository.scanReceipt(file)
    }
}
