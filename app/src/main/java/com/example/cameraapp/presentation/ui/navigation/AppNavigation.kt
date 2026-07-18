package com.example.cameraapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cameraapp.presentation.contract.ScanContract
import com.example.cameraapp.presentation.ui.screen.CameraPreview
import com.example.cameraapp.presentation.ui.screen.ScanningScreen
import com.example.cameraapp.presentation.ui.screen.Screen
import com.example.cameraapp.presentation.viewmodel.ScanViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val scanViewModel: ScanViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Login) {
        composable<Screen.Camera> {
            CameraPreview(
                onImageCaptured = { file ->
                    scanViewModel.handleIntent(ScanContract.Intent.StartScan(file))
                    navController.navigate("scanning")
                },
            )
        }
        composable("scanning") {
            ScanningScreen(viewModel = scanViewModel)
        }

    }
}