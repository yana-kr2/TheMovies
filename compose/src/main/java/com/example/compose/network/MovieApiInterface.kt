package com.example.compose.network

import com.example.compose.model.MovieResponse
import com.example.compose.utils.AppConstant
import com.example.compose.utils.AppConstant.BASE_URL
import com.example.compose.utils.AppConstant.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET(END_POINT)
    suspend fun getMovieResponse(@Query("api_key") apiKey: String = AppConstant.API_KEY): Response<MovieResponse>

}