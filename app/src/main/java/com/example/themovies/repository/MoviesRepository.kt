package com.example.themovies.repository

import com.example.themovies.data.network.MovieApiInterface
import javax.inject.Inject

class MoviesRepository
@Inject constructor(
    private val apiService: MovieApiInterface
) {
    suspend fun getMovies() = apiService.getAllMovies()

    suspend fun getCast(movieId: String) = apiService.getAllCast(movieId = movieId)

    suspend fun getMovieTmdb() = apiService.getMovieResponse()
}