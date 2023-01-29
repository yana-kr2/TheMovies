package com.example.themovies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovies.model.Movie
import com.example.themovies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _response = MutableLiveData<List<Movie>>()
    val response: LiveData<List<Movie>>
        get() = _response

    init {
        getAllMovies()
    }


    private fun getAllMovies() = viewModelScope.launch {
        repository.getMovies().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d(TAG, "Error: ${response.code()}")
            }
        }
    }

}