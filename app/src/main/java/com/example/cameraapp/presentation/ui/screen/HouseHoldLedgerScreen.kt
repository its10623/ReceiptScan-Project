package com.example.cameraapp.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cameraapp.domain.model.Category
import com.example.cameraapp.domain.model.DayInfo
import com.example.cameraapp.domain.model.Receipt
import com.example.cameraapp.domain.model.ReceiptItem
import com.example.cameraapp.presentation.ui.component.BottomNavigationBar
import com.example.cameraapp.presentation.ui.component.CameraFab
import com.example.cameraapp.presentation.ui.component.MonthSummaryCard
import com.example.cameraapp.presentation.ui.component.ReceiptCard
import com.example.cameraapp.presentation.ui.component.ReceiptDetailBottomSheet
import com.example.cameraapp.presentation.ui.component.WeekRow
import com.example.cameraapp.presentation.ui.theme.BgApp
import com.example.cameraapp.presentation.ui.theme.Border
import com.example.cameraapp.presentation.ui.theme.CategoryTransport
import com.example.cameraapp.presentation.ui.theme.Expense
import com.example.cameraapp.presentation.ui.theme.Surface
import com.example.cameraapp.presentation.ui.theme.TextSub
import com.example.cameraapp.presentation.ui.util.formatReceiptAmount
import com.example.cameraapp.presentation.ui.util.formatTodayInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun HouseHoldLedgerScreen(onDetails: (LocalDate) -> Unit = {}) {
    val focusManager = LocalFocusManager.current
    var yearMonth by remember { mutableStateOf(YearMonth.now()) }
    val formatter = DateTimeFormatter.ofPattern("yyyy년 M월")
    val dayHeaders = listOf("일", "월", "화", "수", "목", "금", "토")

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val weeks =
        remember(yearMonth) {
            val firstDay = yearMonth.atDay(1)
            val offset = firstDay.dayOfWeek.value % 7 // 일=0, 월=1 ... 토=6
            val totalDays = yearMonth.lengthOfMonth()

            val days = mutableListOf<LocalDate?>()
            repeat(offset) { days.add(null) }
            for (day in 1..totalDays) days.add(yearMonth.atDay(day))
            while (days.size % 7 != 0) days.add(null)

            days.chunked(7)
        }
    val sampleDayInfo =
        DayInfo(
            date = LocalDate.now(),
            totalExpense = 132000,
            isSelected = true,
        )

    val sampleReceipts =
        listOf(
            Receipt(
                id = 1L,
                category = Category.GROCERY,
                storeName = "이마트 트레이더스",
                purchasedAt = LocalDateTime.of(2026, 6, 17, 19, 24),
                totalPrice = 118000,
                items =
                    listOf(
                        ReceiptItem(
                            id = 1L,
                            category = Category.GROCERY,
                            name = "올리브유",
                            price = 12900,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 2L,
                            category = Category.GROCERY,
                            name = "계란 30구",
                            price = 8900,
                            quantity = 2,
                        ),
                        ReceiptItem(
                            id = 3L,
                            category = Category.GROCERY,
                            name = "닭가슴살",
                            price = 15400,
                            quantity = 3,
                        ),
                        ReceiptItem(
                            id = 4L,
                            category = Category.GROCERY,
                            name = "두유 24입",
                            price = 19800,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 5L,
                            category = Category.GROCERY,
                            name = "화장지 30롤",
                            price = 23200,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 5L,
                            category = Category.GROCERY,
                            name = "화장지 30롤",
                            price = 23200,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 5L,
                            category = Category.GROCERY,
                            name = "화장지 30롤",
                            price = 23200,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 5L,
                            category = Category.GROCERY,
                            name = "화장지 30롤",
                            price = 23200,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 5L,
                            category = Category.GROCERY,
                            name = "화장지 30롤",
                            price = 23200,
                            quantity = 1,
                        ),
                    ),
                createdAt = LocalDateTime.of(2026, 6, 17, 19, 25),
                updatedAt = LocalDateTime.of(2026, 6, 17, 19, 25),
            ),
            Receipt(
                id = 2L,
                category = Category.CAFE_SNACK,
                storeName = "스타벅스 강남점",
                purchasedAt = LocalDateTime.of(2026, 6, 17, 14, 10),
                totalPrice = 8000,
                items =
                    listOf(
                        ReceiptItem(
                            id = 6L,
                            category = Category.CAFE_SNACK,
                            name = "아이스 아메리카노",
                            price = 5000,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 7L,
                            category = Category.CAFE_SNACK,
                            name = "카스텔라",
                            price = 3000,
                            quantity = 1,
                        ),
                    ),
                createdAt = LocalDateTime.of(2026, 6, 17, 14, 11),
                updatedAt = LocalDateTime.of(2026, 6, 17, 14, 11),
            ),
            Receipt(
                id = 3L,
                category = Category.MEAL,
                storeName = "교촌치킨 서초점",
                purchasedAt = LocalDateTime.of(2026, 6, 17, 18, 45),
                totalPrice = 32000,
                items =
                    listOf(
                        ReceiptItem(
                            id = 8L,
                            category = Category.MEAL,
                            name = "허니콤보",
                            price = 23000,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 9L,
                            category = Category.MEAL,
                            name = "콜라 1.25L",
                            price = 3000,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 10L,
                            category = Category.MEAL,
                            name = "치즈볼",
                            price = 6000,
                            quantity = 1,
                        ),
                    ),
                createdAt = LocalDateTime.of(2026, 6, 17, 18, 46),
                updatedAt = LocalDateTime.of(2026, 6, 17, 18, 46),
            ),
            Receipt(
                id = 4L,
                category = Category.TRANSPORT,
                storeName = "카카오택시",
                purchasedAt = LocalDateTime.of(2026, 6, 17, 9, 15),
                totalPrice = 12400,
                items =
                    listOf(
                        ReceiptItem(
                            id = 11L,
                            category = Category.TRANSPORT,
                            name = "택시 요금",
                            price = 12400,
                            quantity = 1,
                        ),
                    ),
                createdAt = LocalDateTime.of(2026, 6, 17, 9, 16),
                updatedAt = LocalDateTime.of(2026, 6, 17, 9, 16),
            ),
            Receipt(
                id = 5L,
                category = Category.MEAL,
                storeName = "맥도날드 강남점",
                purchasedAt = LocalDateTime.of(2026, 6, 17, 12, 30),
                totalPrice = 15500,
                items =
                    listOf(
                        ReceiptItem(
                            id = 12L,
                            category = Category.MEAL,
                            name = "빅맥 세트",
                            price = 9200,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 13L,
                            category = Category.MEAL,
                            name = "맥너겟 6조각",
                            price = 4300,
                            quantity = 1,
                        ),
                        ReceiptItem(
                            id = 14L,
                            category = Category.MEAL,
                            name = "애플파이",
                            price = 2000,
                            quantity = 1,
                        ),
                    ),
                createdAt = LocalDateTime.of(2026, 6, 17, 12, 31),
                updatedAt = LocalDateTime.of(2026, 6, 17, 12, 31),
            ),
        )

    var selectedReceipt by remember { mutableStateOf<Receipt?>(null) }

    if (selectedReceipt != null) {
        ReceiptDetailBottomSheet(
            selectedReceipt!!,
            onDismiss = { selectedReceipt = null },
        )
    }

    Scaffold(
        containerColor = BgApp,
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            CameraFab(modifier = Modifier.offset(y = 28.dp))
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier =
            Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    focusManager.clearFocus()
                },
    ) { paddingValues ->
        LazyColumn(
            modifier =
                Modifier
                    .padding(paddingValues),
        ) {
            item {
                Row(
                    modifier =
                        Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { yearMonth = yearMonth.minusMonths(1) },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ChevronLeft,
                            contentDescription = "previous month",
                            tint = TextSub,
                        )
                    }

                    Text(
                        text = yearMonth.format(formatter),
                        style = MaterialTheme.typography.headlineMedium,
                    )

                    IconButton(
                        onClick = { yearMonth = yearMonth.plusMonths(1) },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ChevronRight,
                            contentDescription = "next month",
                            tint = TextSub,
                        )
                    }
                }
            }

            item {
                MonthSummaryCard(
                    month = "6",
                    totalIncome = 3150000,
                    totalExpense = 1284500,
                    remainingAmount = 1865500,
                )
            }

            item {
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                    colors = CardDefaults.cardColors(Surface),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Border),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            dayHeaders.forEach { header ->
                                Text(
                                    text = header,
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    color =
                                        when (header) {
                                            "일" -> Expense
                                            "토" -> CategoryTransport
                                            else -> TextSub
                                        },
                                )
                            }
                        }

                        weeks.forEach { week ->
                            WeekRow(
                                days = week,
                                selectedDate = selectedDate,
                                onDetails = onDetails,
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier =
                        Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = formatTodayInfo(LocalDate.now()),
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                    )

                    Text(
                        text = formatReceiptAmount(sampleDayInfo.totalExpense, isIncome = false),
                        color = Expense,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                    )
                }
            }

            item {
                sampleReceipts.forEach { receipt ->
                    ReceiptCard(
                        receipt,
                        onClick = { selectedReceipt = it },
                    )
                }
            }
        }
    }
}
