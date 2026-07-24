package com.smoothsm.cameraapp.domain.model

data class ReceiptItem(
    val id: Long,
    val name: String? = null,
    val price: Int? = 0,
    val quantity: Int = 1,
)
