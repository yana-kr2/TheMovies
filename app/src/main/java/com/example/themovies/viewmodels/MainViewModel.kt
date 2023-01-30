package com.example.themovies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovies.model.Movie.Movie
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
    //
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()


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
                val errorBody: String = response.errorBody().toString()
                _uiState.update {
                    it.copy(errorMsg = errorBody)
                }
                Log.d(TAG, "Error: ${response.code()}")
            }
        }
    }

    fun onErrorMessageShown() {
        _uiState.update {
            it.copy(errorMsg = null)
        }
    }


    data class MainUiState(
        val isLoading: Boolean = false,
        val errorMsg: String? = null
    )

}