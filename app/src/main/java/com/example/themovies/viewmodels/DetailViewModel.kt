package com.example.themovies.viewmodels

import androidx.lifecycle.ViewModel
import com.example.themovies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    companion object {
        const val TAG = "DetailViewModel"
    }

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()


    var job: Job? = null
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
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

//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }
}

data class DetailUiState (
    val isLoading: Boolean = false,
    val errorMsg: String? = null
        )