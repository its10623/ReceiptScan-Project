package com.example.cameraapp.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cameraapp.presentation.ui.theme.Border
import com.example.cameraapp.presentation.ui.theme.Primary
import com.example.cameraapp.presentation.ui.theme.Shape
import java.text.DecimalFormat

@Composable
fun MonthSummaryCard(
    month: String,
    totalIncome: Int,
    totalExpense: Int,
    remainingAmount: Int,
) {
    val formatter = DecimalFormat("#,###")
    var plusMinus = "+"

    Card(
        modifier =
            Modifier
                .padding(12.dp)
                .fillMaxWidth(),
        shape = Shape.SummaryCard,
        colors = CardDefaults.cardColors(containerColor = Primary),
        border = BorderStroke(1.dp, Border),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "${month}월 이번달 남은 금액",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Text(
                text = "$plusMinus${formatter.format(remainingAmount)}원",
                style = MaterialTheme.typography.displayLarge,
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier =
                    Modifier
                        .padding(top = 12.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SummaryItemCard(
                    "수입",
                    "${formatter.format(totalIncome)}원",
                    Modifier
                        .padding(end = 4.dp)
                        .weight(1f),
                )
                SummaryItemCard(
                    "지출",
                    "${formatter.format(totalExpense)}원",
                    Modifier
                        .padding(start = 4.dp)
                        .weight(1f),
                )
            }
        }
    }
}
