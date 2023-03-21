package com.example.compose.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compose.model.Movie
import com.example.compose.model.Movies.MovieItem
import com.example.compose.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }


    private val _movieList = MutableLiveData<List<MovieItem>>()
    val movieList: LiveData<List<MovieItem>>
    get() = _movieList

    var job: Job? = null
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }


    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val result = repository.getMovies()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    Log.d(TAG,"Result ${result.body()}")
                    _movieList.postValue(result.body())
                } else {

                    }
                }
            }
        }
    }



