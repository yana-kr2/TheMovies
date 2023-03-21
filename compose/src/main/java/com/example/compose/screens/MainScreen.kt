package com.example.compose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.compose.model.Movies.MovieItem
import com.example.compose.ui.theme.TheMoviesTheme
import com.example.compose.viewmodels.MainViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    viewModel.getAllMovies()
    val allMovies = viewModel.movieList.observeAsState(listOf()).value

    LazyColumn {
        items(allMovies.take(10)) { item ->  
            com.example.compose.screens.MovieItem(item = item)
            }
        }
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

    }

}

@Composable
fun MovieItem(item: MovieItem) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = item.name)
        Text(text = item.id.toString())
        
    }

}