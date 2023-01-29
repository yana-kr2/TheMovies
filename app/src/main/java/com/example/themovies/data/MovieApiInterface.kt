package com.example.themovies.data

import com.example.themovies.model.MovieResponse
import com.example.themovies.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {
    @GET("/movie/top_rated?api_key=${API_KEY}")
    fun getMovieList(): Call<MovieResponse>
}