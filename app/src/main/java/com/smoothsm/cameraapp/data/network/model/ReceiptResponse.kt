package com.smoothsm.cameraapp.data.network.model

data class ScanApiResponse(
    val success: Boolean,
    val receipt: ReceiptResponse?,
)

data class ReceiptResponse(
    val id: Long? = null,
    val storeName: String? = null,
    val totalPrice: Int? = null,
    val purchaseAt: String? = null,
    val category: String? = null,
    val items: List<ItemResponse>? = null,
)
