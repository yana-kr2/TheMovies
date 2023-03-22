package com.example.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.compose.model.Movie
import com.example.compose.ui.theme.Pale_Black
import com.example.compose.ui.theme.TheMoviesTheme
import com.example.compose.utils.AppConstant
import com.example.compose.viewmodels.MainViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    viewModel.getAllMovies()
    val allMovies = viewModel.movieList.observeAsState(listOf()).value

    LazyColumn(
        modifier = Modifier
            .background(Pale_Black)
    ) {
        items(allMovies.take(50)) { item ->
            com.example.compose.screens.MovieItem(item = item)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMain() {
    TheMoviesTheme {

    }

}

@Composable
fun MovieItem(item: Movie) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .background(Pale_Black)
            .padding(8.dp)
            .clickable {  },
    ) {
        Row(
            modifier = Modifier
                .background(Pale_Black)
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Image(
                painter = rememberImagePainter(AppConstant.IMAGE_BASE_URL + item.posterPath),
                contentDescription = "",
                modifier = Modifier.size(128.dp)
            )

            Column {
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(
                        text = "Rating: ${item.voteAverage}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    Text(
                        text = "Release date: ${item.releaseDate}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row() {

            }

        }

    }

}