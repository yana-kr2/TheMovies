package com.example.themovies.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.themovies.repository.MoviesRepository
import com.example.themovies.viewmodels.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [30])
@ExperimentalCoroutinesApi
class MainViewModelUnitTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var viewModel: MainViewModel

    @Mock
    lateinit var apiRepo: MoviesRepository

    @Mock
    private lateinit var observer: Observer<MainViewModel.MainUiState>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(repository = apiRepo)
    }

}