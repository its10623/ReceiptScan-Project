package com.example.cameraapp.domain.model

import java.time.LocalDateTime

data class Receipt(
    val id: Long? = null,
    val storeName: String? = null,
    val purchasedAt: LocalDateTime,
    val totalPrice: Int,
    val items: List<ReceiptItem>,
    val imagePath: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val syncStatus: SyncStatus = SyncStatus.PENDING
)