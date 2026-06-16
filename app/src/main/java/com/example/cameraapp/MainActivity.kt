package com.example.cameraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cameraapp.presentation.ui.screen.CameraPreview
import com.example.cameraapp.presentation.ui.screen.HouseHoldLedgerScreen
import com.example.cameraapp.presentation.ui.screen.LoginScreen
import com.example.cameraapp.presentation.ui.theme.CameraAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CameraAppTheme {
                HouseHoldLedgerScreen()
            }
        }
    }
}
