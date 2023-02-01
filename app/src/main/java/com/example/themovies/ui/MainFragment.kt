package com.example.themovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovies.adapters.MovieAdapter
import com.example.themovies.databinding.FragmentMainBinding
import com.example.themovies.utils.extensions.collectIn
import com.example.themovies.utils.extensions.showSnackBar
import com.example.themovies.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private var mBinding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModels()
    private lateinit var tvShowAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root
        mBinding = binding
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun setupView() {
        setUpRv()
        subscribeUi()


    }

    private fun subscribeUi() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.uiState.collectIn(viewLifecycleOwner) { uiState ->
                mBinding?.progressLoading?.root?.isVisible = uiState.isLoading
                uiState.errorMsg?.let {
                    activity?.showSnackBar(it)
                    viewModel.onErrorMessageShown()
                }
            }
        }
    }


    private fun setUpRv() {
        tvShowAdapter = MovieAdapter()
        val manager = GridLayoutManager(activity,2,GridLayoutManager.VERTICAL, false)

        mBinding?.rvMoviesList.apply {
            this?.adapter = tvShowAdapter
            this?.layoutManager = manager
            this?.setHasFixedSize(true)
        }
        viewModel.movieList.observe(viewLifecycleOwner) {
            tvShowAdapter.movies = it
        }
        viewModel.getAllMovies()
    }
}