package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.compose.navigation.SetupNavHost
import com.example.compose.ui.theme.TheMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMoviesTheme {
                val navController = rememberNavController()
                SetupNavHost(navController = navController)

           
            }
        }
    }
}