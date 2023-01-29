package com.example.themovies.api

import com.example.themovies.model.MovieResponse
import com.example.themovies.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/movie/top_rated?api_key=$API_KEY")
    suspend fun getMovies(): Response<MovieResponse>
}