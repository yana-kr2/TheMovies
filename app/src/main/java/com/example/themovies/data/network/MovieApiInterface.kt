package com.example.themovies.data.network

import com.example.themovies.model.cast.CastItem
import com.example.themovies.model.movie.Movie
import com.example.themovies.model.tmdb.MovieResponse
import com.example.themovies.utils.AppConstants.END_POINT
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiInterface {
    @GET(END_POINT)
    suspend fun getAllMovies(): Response<List<Movie>>

    @GET("shows/{movie_id}/cast")
    suspend fun getAllCast(@Path("movie_id")movieId: String): Response<ArrayList<CastItem>>


    @GET("/3/movie/top_rated?api_key=ab491442ff959325618c99b748ce3058")
    suspend fun getMovieResponse(): Response<MovieResponse>

}

