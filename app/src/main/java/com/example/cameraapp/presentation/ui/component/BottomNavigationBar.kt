package com.example.cameraapp.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cameraapp.presentation.ui.theme.Surface

@Preview
@Composable
fun BottomNavigationBar(

) {
    Surface(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars),
        color = Surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f)) {
                NavIcon(
                    text = "가계부",
                    icon = Icons.Rounded.CalendarMonth,
                    modifier = Modifier.weight(1f),
                    onClick = {}
                )
                NavIcon(
                    text = "통계",
                    icon = Icons.Rounded.BarChart,
                    modifier = Modifier.weight(1f),
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.width(60.dp))

            Row(modifier = Modifier.weight(1f)) {
                NavIcon(
                    text = "자산",
                    icon = Icons.Rounded.AccountBalanceWallet,
                    modifier = Modifier.weight(1f),
                    onClick = {}
                )
                NavIcon(
                    text = "더보기",
                    icon = Icons.Rounded.MoreHoriz,
                    modifier = Modifier.weight(1f),
                    onClick = {}
                )
            }
        }
    }
}