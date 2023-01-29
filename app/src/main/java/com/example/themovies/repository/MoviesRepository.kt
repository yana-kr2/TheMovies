package com.example.themovies.repository

import com.example.themovies.api.ApiService
import javax.inject.Inject

class MoviesRepository
    @Inject constructor(
        private val apiService: ApiService
    ) {
        suspend fun getMovies() = apiService.getMovies()
}