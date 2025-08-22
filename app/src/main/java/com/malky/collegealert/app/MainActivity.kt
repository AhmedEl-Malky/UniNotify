package com.malky.collegealert.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.malky.collegealert.app.theme.CollegeAlertTheme
import dagger.hilt.EntryPoint

@EntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollegeAlertTheme {
                App()
            }
        }
    }
}