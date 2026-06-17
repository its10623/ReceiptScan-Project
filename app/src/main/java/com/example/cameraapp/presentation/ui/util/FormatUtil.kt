package com.example.cameraapp.presentation.ui.util

import java.text.DecimalFormat
import kotlin.math.floor

fun formatCalendarAmount(amount: Int, isIncome: Boolean): String {
    val sign = if (isIncome) "+" else "-"
    return if (amount >= 10000) {
        val man = amount / 10000.0
        val formatted = if (man == floor(man)) man.toInt().toString()
        else String.format("%.1f", man)
        "${sign}${formatted}만"
    } else {
        "${sign}${amount}"
    }
}

fun formatReceiptAmount(amount: Int): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format("-$amount")
}
