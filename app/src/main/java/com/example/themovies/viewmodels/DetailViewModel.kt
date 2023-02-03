package com.example.themovies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.themovies.model.cast.CastItem
import com.example.themovies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MoviesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    companion object {
        const val TAG = "DetailViewModel"
    }

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()
//    private val movieId = savedStateHandle.getLiveData<Int>("id")

    val list = MutableLiveData<List<CastItem>>()

    // I know, that it is a wrong way. It's unwelcome method
    // but, i don't follow, why savedStateHandle doesn't work (like as I write above)
    var movieId: Int = -1


    fun getAllCast() {
        job = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            showLoading(true)
            val response = repository.getCast(movieId.toString())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    list.postValue(response.body())
                } else {
                    _uiState.update {
                        it.copy(errorMsg = response.errorBody().toString())
                    }
                }
            }
            showLoading(false)
        }
    }


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

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}

data class DetailUiState(
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)