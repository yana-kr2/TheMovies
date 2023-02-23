package com.example.themovies.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.model.tmdb.Movie
import com.example.themovies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()


    val movieList = MutableLiveData<List<Movie>>()

    var job: Job? = null
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }


    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            showLoading(true)
            val result = repository.getMovieTmdb()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    Log.d(TAG,"Result ${result.body()}")
                    movieList.postValue(result.body()!!.results)
                } else {
                    _uiState.update {
                        it.copy(errorMsg = result.errorBody().toString())
                    }
                }
            }
            showLoading(false)
        }
    }


    fun onErrorMessageShown() {
        _uiState.update {
            it.copy(errorMsg = null)
        }
    }

    private fun showLoading(value: Boolean) {
        _uiState.update {
            it.copy(isLoading = value)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    data class MainUiState(
        val isLoading: Boolean = false,
        val errorMsg: String? = null
    )

}


