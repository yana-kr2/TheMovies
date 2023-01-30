package com.example.themovies.api

import com.example.themovies.model.Movie.MovieResponse
import com.example.themovies.utils.AppConstants.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
//    @GET("/3/movie/top_rated?api_key=${API_KEY}")
    suspend fun getMovies(): Response<MovieResponse>
}