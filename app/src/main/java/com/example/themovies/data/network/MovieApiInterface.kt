package com.example.themovies.data.network

import com.example.themovies.model.movie.Movie
import com.example.themovies.utils.AppConstants.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiInterface {
    @GET(END_POINT)
    suspend fun getAllMovies(): Response<List<Movie>>

//    @GET("shows/${id}?embed=cast")
//    suspend fun getAllCast(): Response<List<Actor>>

}
