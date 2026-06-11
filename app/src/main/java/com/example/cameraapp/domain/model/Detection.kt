package com.example.cameraapp.domain.model

data class Detection(
    val category: String,
    val confidence: Float,      //신뢰성
    val distance: Float
)
