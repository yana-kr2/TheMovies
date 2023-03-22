package com.example.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.compose.model.Movie
import com.example.compose.navigation.Screens
import com.example.compose.ui.theme.Pale_Black
import com.example.compose.ui.theme.TheMoviesTheme
import com.example.compose.utils.AppConstant
import com.example.compose.viewmodels.MainViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.movieList.observeAsState(listOf()).value

    Column(
        modifier = Modifier
            .background(Pale_Black)
    ) {
        MainTitle()
        LazyColumn(
            modifier = Modifier
                .background(Pale_Black)
        ) {

            items(allMovies.take(50)) { item ->
                MovieItem(movie = item, navController = navController)

            }
        }
    }
}


@Composable
fun MainTitle() {
    Text(
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body2,
        text = "TOP RATED",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
    )
}

@Composable
fun MovieItem(movie: Movie, navController: NavController) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .background(Pale_Black)
            .padding(8.dp)
            .clickable {
                navController.navigate(Screens.Detail.route + "/${movie.id}")
            },
    ) {
        Row(
            modifier = Modifier
                .background(Pale_Black)
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Image(
                painter = rememberImagePainter(AppConstant.IMAGE_BASE_URL + movie.posterPath),
                contentDescription = "",
                modifier = Modifier.size(128.dp)
            )

            Column {
                Text(
                    text = movie.title,
                    color = Color.White,
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Text(
                        text = "Rating: ${movie.voteAverage}",
                        color = Color.White,
                        style = MaterialTheme.typography.h2
                    )
                }
                Row {
                    Text(
                        text = "Release date: ${movie.releaseDate}",
                        color = Color.White,
                        style = MaterialTheme.typography.h2
                    )
                }
            }

        }

    }

}

@Composable
@Preview(showBackground = true)
fun PreviewMain() {
    TheMoviesTheme {

    }

}