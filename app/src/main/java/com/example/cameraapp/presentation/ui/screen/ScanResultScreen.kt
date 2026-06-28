package com.example.cameraapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cameraapp.presentation.viewmodel.ScanViewModel

@Composable
fun ScanResultScreen(
    viewModel: ScanViewModel = hiltViewModel(),
) {
    val scanResult by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(text = "가게명: ${scanResult.receipt?.storeName ?: "-"}")
        Text(text = "합계: ${scanResult.receipt?.totalPrice}원")
        Text(text = "구매일시: ${scanResult.receipt?.purchaseAt}")
        Text(text = "카테고리: ${scanResult.receipt?.category ?: "-"}")
        Text(text = "아이템 목록:")
        scanResult.receipt?.items?.forEach { item ->
            Text(text = "  - ${item.name} / ${item.price}원 / ${item.quantity}개")
        }
    }
}
