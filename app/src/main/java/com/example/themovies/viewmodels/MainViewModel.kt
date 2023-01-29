package com.example.themovies.viewmodels

import androidx.lifecycle.ViewModel
import com.example.themovies.data.MovieApiInterface
import com.example.themovies.data.MovieApiService
import com.example.themovies.model.Movie
import com.example.themovies.model.MovieResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState.asStateFlow()


    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {


            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback (response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })

    }


    private fun showLoading(value: Boolean) {
        _uiState.update {
            it.copy(isLoading = value)
        }
    }


    fun onErrorMessageShown() {
        _uiState.update {
            it.copy(errorMsg = null)
        }
    }


    data class MainUIState(
        val isListLoading: Boolean = false,
        val isDataLoading: Boolean = false,
        val isLoading: Boolean = false,
        val errorMsg: String? = null
    )
}