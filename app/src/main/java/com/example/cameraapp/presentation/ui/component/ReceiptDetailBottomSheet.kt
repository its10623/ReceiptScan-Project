package com.example.cameraapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.cameraapp.presentation.ui.theme.Border
import com.example.cameraapp.presentation.ui.theme.Expense
import com.example.cameraapp.presentation.ui.theme.ModalBg
import com.example.cameraapp.presentation.ui.theme.Primary
import com.example.cameraapp.presentation.ui.theme.PrimarySoft
import com.example.cameraapp.presentation.ui.theme.Shape
import com.example.cameraapp.presentation.ui.theme.Surface
import com.example.cameraapp.presentation.ui.theme.TextSub
import com.example.cameraapp.presentation.ui.util.formatDayOfMonth
import com.example.cameraapp.presentation.ui.util.formatItemAmount
import com.example.cameraapp.presentation.ui.util.formatReceiptAmount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptDetailBottomSheet(
    receipt: Receipt,
    onDismiss: () -> Unit,
) {
    val formatCreatedDate = formatDayOfMonth(receipt.createdAt)
    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )

    var expanded by remember { mutableStateOf(false) }
    val visibleItems = if (expanded) receipt.items else receipt.items.take(5)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        sheetState = sheetState,
        scrimColor = ModalBg,
        dragHandle = null,
        containerColor = Surface,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
        ) {
            Row(
                modifier =
                    Modifier
                        .padding(horizontal = 12.dp, vertical = 20.dp)
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(52.dp)
                            .clip(shape = Shape.IconBox)
                            .background(receipt.category.chipBg),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = receipt.category.icon,
                        contentDescription = receipt.category.label,
                        tint = receipt.category.iconTint,
                        modifier =
                            Modifier
                                .size(46.dp),
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
                        style = MaterialTheme.typography.headlineMedium,
                        modifier =
                            Modifier
                                .padding(4.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .clip(shape = Shape.Chip)
                                    .background(receipt.category.chipBg)
                                    .padding(horizontal = 8.dp, vertical = 2.dp),
                        ) {
                            Text(
                                text = receipt.category.label,
                                style = MaterialTheme.typography.bodySmall,
                                color = receipt.category.iconTint,
                            )
                        }

                        Text(
                            text = formatCreatedDate,
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSub,
                            modifier =
                                Modifier
                                    .padding(horizontal = 4.dp, vertical = 2.dp),
                        )
                    }
                }

                IconButton(
                    onClick = onDismiss,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "닫기",
                        modifier =
                            Modifier
                                .size(35.dp),
                        tint = TextSub,
                    )
                }
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "결제 금액",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSub,
                )

                Text(
                    text = formatReceiptAmount(receipt.totalPrice, isIncome = false),
                    style = MaterialTheme.typography.displayLarge,
                    color = Expense,
                )
            }

            HorizontalDivider(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                thickness = 1.dp,
                color = Border,
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier =
                        Modifier
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(PrimarySoft)
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "✦",
                        style = MaterialTheme.typography.labelSmall,
                        color = Primary,
                    )
                }

                Text(
                    text = "AI가 ${receipt.items.size}개 품목을 인식했어요",
                    style = MaterialTheme.typography.bodySmall,
                    color = Primary,
                    modifier =
                        Modifier
                            .padding(start = 2.dp),
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "수량 · 단가",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSub,
                    modifier =
                        Modifier
                            .padding(end = 2.dp),
                )
            }
            LazyColumn(
                modifier = Modifier.weight(1f),
            ) {
                items(visibleItems) { item ->
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column {
                            Text(
                                text = item.name ?: "인식 오류",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Text(
                                text = "${item.quantity}개 · ${formatItemAmount(item.price)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSub,
                            )
                        }
                        Text(
                            text = formatItemAmount(item.price),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    HorizontalDivider(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                        thickness = 1.dp,
                        color = Border,
                    )
                }
                item {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        if (receipt.items.size > 5) {
                            Text(
                                text = if (expanded) "접기" else "${receipt.items.size - 5}개 항목 더보기",
                                modifier = Modifier.clickable { expanded = !expanded },
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSub,
                            )
                            Icon(
                                imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "",
                                tint = TextSub,
                            )
                        }
                    }
                }
            }
            HorizontalDivider(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                thickness = 1.dp,
                color = Border,
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SecondaryButton(
                    text = "내용 수정",
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(end = 6.dp),
                )
                PrimaryButton(
                    text = "영수증 보기",
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(start = 6.dp),
                )
            }
        }
    }
}
