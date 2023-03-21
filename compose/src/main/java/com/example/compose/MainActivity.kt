package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.compose.navigation.SetupNavHost
import com.example.compose.ui.theme.TheMoviesTheme
import com.example.compose.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMoviesTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel by viewModels()
                SetupNavHost(navController = navController, viewModel = viewModel)

           
            }
        }
    }
}