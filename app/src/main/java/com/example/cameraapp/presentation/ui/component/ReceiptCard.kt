package com.example.cameraapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cameraapp.domain.model.Receipt
import com.example.cameraapp.domain.model.chipBg
import com.example.cameraapp.domain.model.icon
import com.example.cameraapp.domain.model.iconTint
import com.example.cameraapp.domain.model.label
import com.example.cameraapp.presentation.ui.theme.Shape
import com.example.cameraapp.presentation.ui.theme.Surface
import com.example.cameraapp.presentation.ui.theme.TextSub
import com.example.cameraapp.presentation.ui.util.formatReceiptAmount
import com.example.cameraapp.presentation.ui.util.formatReceiptPurchaseAt

@Composable
fun ReceiptCard(
    receipt: Receipt,
    onClick: (Receipt) -> Unit,
) {
    val formatTime = formatReceiptPurchaseAt(receipt.purchaseAt)

    Card(
        modifier =
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        shape = Shape.Card,
        colors = CardDefaults.cardColors(containerColor = Surface),
        onClick = { onClick(receipt) },
    ) {
        Row(
            modifier =
                Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(40.dp)
                        .clip(shape = Shape.IconBox)
                        .background(receipt.category.chipBg),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = receipt.category.icon,
                    contentDescription = receipt.category.label,
                    tint = receipt.category.iconTint,
                )
            }
            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
            ) {
                Text(
                    text = receipt.storeName ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = "${receipt.category.label} · $formatTime",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSub,
                )
            }

            Text(
                text = formatReceiptAmount(receipt.totalPrice ?: 0, isIncome = false),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
