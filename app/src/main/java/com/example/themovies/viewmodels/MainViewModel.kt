package com.example.themovies.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update



@HiltViewModel
class MainViewModel : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState.asStateFlow()


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