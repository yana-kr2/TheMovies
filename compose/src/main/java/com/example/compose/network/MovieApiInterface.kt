package com.example.compose.network

import com.example.compose.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/top_rated?api_key=ab491442ff959325618c99b748ce3058")
    suspend fun getMovieResponse(): Response<MovieResponse>
}