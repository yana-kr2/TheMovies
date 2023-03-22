package com.example.compose.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.navigation.Screens
import com.example.compose.ui.theme.Dark_Gray
import com.example.compose.ui.theme.Pale_Black
import com.example.compose.viewmodels.MainViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel) {
    var startAnimate by remember {
        mutableStateOf(false)
    }
    val alphaAnimations = animateFloatAsState(
        targetValue = if (startAnimate) 1f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )
    LaunchedEffect(key1 = true) {
        startAnimate = true
        viewModel.getAllMovies()
        delay(2000)
        navController.navigate(Screens.Main.route)
    }



    SetupSplash(alpha = alphaAnimations.value)
}

@Composable
fun SetupSplash(alpha: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pale_Black),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(220.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Default.Star,
            contentDescription = "",
            tint = Dark_Gray
        )
        Text(
            text = "The Movies",
            style = MaterialTheme.typography.body2,
            color = Color.White,
            textAlign = TextAlign.End
        )
    }
}