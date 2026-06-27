package com.example.cameraapp.data.network.model

import com.example.cameraapp.domain.model.Category
import com.example.cameraapp.domain.model.Receipt
import com.example.cameraapp.domain.model.ReceiptItem
import java.time.LocalDateTime

fun ReceiptResponse.toDomain(): Receipt = Receipt(
    id = id,
    storeName = storeName,
    totalPrice = totalPrice,
    purchaseAt = purchaseAt,
    category = category?.let { runCatching { Category.valueOf(it) }.getOrNull() },
    items = items?.map {
        ReceiptItem(
            id = it.id ?: 0L,
            name = it.name,
            price = it.price,
            quantity = it.quantity,
        )
    } ?: emptyList(),
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now(),
)