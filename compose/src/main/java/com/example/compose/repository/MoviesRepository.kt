package com.example.compose.repository

import com.example.compose.network.MovieApiInterface
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiService: MovieApiInterface
) {

    suspend fun getMovies() = apiService.getAllMovies()

    suspend fun getMovieTmdb() = apiService.getMovieResponse()

}