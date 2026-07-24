package com.smoothsm.cameraapp.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun WeekRow(
    days: List<LocalDate?>,
    selectedDate: LocalDate?,
    onDetails: (LocalDate) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
    ) {
        days.forEach { date ->
            DayCell(
                date = date,
                isToday = date == LocalDate.now(),
                isSelected = date == selectedDate,
                onClick = onDetails,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
