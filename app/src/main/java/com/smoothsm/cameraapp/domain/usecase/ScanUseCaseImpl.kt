package com.smoothsm.cameraapp.domain.usecase

import com.smoothsm.cameraapp.domain.model.Receipt
import com.smoothsm.cameraapp.domain.repository.ScanRepository
import java.io.File
import javax.inject.Inject

class ScanUseCaseImpl
    @Inject
    constructor(
        private val scanRepository: ScanRepository,
    ) : ScanUseCase {
        override suspend fun scanReceipt(file: File): Receipt {
            return scanRepository.scanReceipt(file)
        }
    }
