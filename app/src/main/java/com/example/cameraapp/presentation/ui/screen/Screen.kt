package com.example.cameraapp.presentation.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Camera : Screen("camera")
    object ScanResult : Screen("scanResult")
    object ResultEdit : Screen("resultEdit")

    sealed class Bottom(val bottomRoute: String, val title: String, val icon: ImageVector) {
        object Ledger : Bottom("ledger", "가계부", Icons.Rounded.CalendarMonth)
        object Assets : Bottom("assets", "자산", Icons.Rounded.AccountBalanceWallet)
        object Statistics : Bottom("statistics", "통계", Icons.Rounded.BarChart)
        object Profile : Bottom("profile", "프로필", Icons.Rounded.AccountCircle)
    }
    companion object {
        val bottomNavScreen = listOf(
            Bottom.Ledger,
            Bottom.Assets,
            Bottom.Statistics,
            Bottom.Profile
        )
    }
}
