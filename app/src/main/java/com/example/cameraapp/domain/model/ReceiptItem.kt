package com.example.cameraapp.domain.model

data class ReceiptItem(
    val id: Long,
    val category: Category? = Category.ETC,
    val name: String? = null,
    val price: Int,
    val quantity: Int = 1,
)