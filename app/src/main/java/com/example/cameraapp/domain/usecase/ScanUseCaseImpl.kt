package com.example.cameraapp.domain.usecase

import com.example.cameraapp.domain.model.Receipt
import com.example.cameraapp.domain.repository.ScanRepository
import java.io.File
import javax.inject.Inject

class ScanUseCaseImpl
@Inject
constructor(
        private val scanRepository: ScanRepository
) : ScanUseCase {
    override suspend fun scanReceipt(file: File): Receipt {
        return scanRepository.scanReceipt(file)
    }
}
