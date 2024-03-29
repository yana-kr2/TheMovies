package com.example.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.screens.DetailScreen
import com.example.compose.screens.MainScreen
import com.example.compose.screens.SplashScreen
import com.example.compose.viewmodels.MainViewModel

sealed class Screens(val route: String) {
    object Main : Screens(route = com.example.compose.utils.Screens.MAIN_SCREEN)
    object Detail : Screens(route = com.example.compose.utils.Screens.DETAIL_SCREEN)

    object Splash: Screens(route = com.example.compose.utils.Screens.SPLASH_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)

        }
        composable(route = Screens.Detail.route + "/{Id}") { navBackStackEntry ->
        DetailScreen(navController = navController, viewModel = viewModel, movieId = navBackStackEntry.arguments?.getString("Id") ?: "1")

        }
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel)

        }
    }

}