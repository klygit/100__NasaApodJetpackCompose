package com.khangly.nasaapodjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khangly.nasaapodjetpackcompose.ui.theme.NasaApodJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaApodJetpackComposeTheme {
                RootScreen()
            }
        }
    }
}