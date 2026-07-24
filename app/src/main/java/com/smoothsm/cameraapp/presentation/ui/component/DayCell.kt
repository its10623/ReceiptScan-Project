package com.smoothsm.cameraapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.smoothsm.cameraapp.presentation.ui.theme.CategoryTransport
import com.smoothsm.cameraapp.presentation.ui.theme.Expense
import com.smoothsm.cameraapp.presentation.ui.theme.Ink
import com.smoothsm.cameraapp.presentation.ui.theme.Primary
import com.smoothsm.cameraapp.presentation.ui.theme.PrimaryTint
import com.smoothsm.cameraapp.presentation.ui.util.formatCalendarAmount
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun DayCell(
    date: LocalDate?,
    modifier: Modifier = Modifier,
    isToday: Boolean = false,
    isSelected: Boolean = false,
    income: Int = 0,
    expense: Int = 0,
    onClick: (LocalDate) -> Unit,
) {
    if (date == null) {
        Box(modifier = modifier.aspectRatio(1f))
        return
    }

    val isSunday = date.dayOfWeek == DayOfWeek.SUNDAY
    val isSaturday = date.dayOfWeek == DayOfWeek.SATURDAY

    val dateTextColor =
        when {
            isToday -> Color.White
            isSelected -> Primary
            isSunday -> Expense
            isSaturday -> CategoryTransport
            else -> Ink
        }

    Box(
        modifier =
            modifier
                .aspectRatio(1f),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .clickable { onClick(date) }
                        .background(
                            color =
                                when {
                                    isToday -> Primary
                                    isSelected -> PrimaryTint
                                    else -> Color.Transparent
                                },
                            shape = CircleShape,
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = dateTextColor,
                    textAlign = TextAlign.Center,
                )
            }

            if (income > 0) {
                Text(
                    text = formatCalendarAmount(income, isIncome = true),
                    style = MaterialTheme.typography.labelMedium,
                    color = Primary,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }

            if (expense > 0) {
                Text(
                    text = formatCalendarAmount(expense, isIncome = false),
                    style = MaterialTheme.typography.labelMedium,
                    color = Expense,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
