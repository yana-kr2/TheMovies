package com.example.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.compose.ui.theme.Pale_Black
import com.example.compose.utils.AppConstant
import com.example.compose.viewmodels.MainViewModel


@Composable
fun DetailScreen(navController: NavController, viewModel: MainViewModel, movieId: String) {
    val currentMovie = viewModel.movieList.observeAsState(listOf()).value
        .firstOrNull {
            it.id == movieId.toInt()
        }
    Surface(
        modifier = Modifier
            .background(Pale_Black)
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 12.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .background(Pale_Black)
        ) {

            item {
                Image(
                    painter = rememberImagePainter(AppConstant.IMAGE_BASE_URL + currentMovie?.posterPath),
                    contentDescription = "",
                    modifier = Modifier.size(512.dp)

                )

                Text(
                    textAlign = TextAlign.Center,
                    text = currentMovie?.title ?: "Unknown title",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp),

                )

                Text(
                    text = currentMovie?.overview ?: "Unknown overview",
                    color = Color.White,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                )
            }
        }

    }
}