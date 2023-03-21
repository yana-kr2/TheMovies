package com.example.compose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.TheMoviesTheme

@Composable
fun MainScreen() {
    Test()

}

@Composable
fun Test() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "TEST")

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMain() {
    TheMoviesTheme {
        Test()

    }
}