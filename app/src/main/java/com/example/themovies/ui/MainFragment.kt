package com.example.themovies.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themovies.adapters.MovieAdapter
import com.example.themovies.databinding.FragmentMainBinding
import com.example.themovies.utils.extensions.collectIn
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
    private var recycleView: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root
        mBinding = binding
        setUpRv()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun setupView() {


    }

    private fun subscribeUi(){
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.uiState.collectIn(viewLifecycleOwner) {
                uiState ->
                mBinding?.progressLoading?.root?.isVisible = uiState.isLoading
            }
        }
    }


    private fun setUpRv() {
        tvShowAdapter = MovieAdapter()

        mBinding?.rvMoviesList.apply {
            this?.adapter = tvShowAdapter
            this?.layoutManager = LinearLayoutManager(
                activity, LinearLayoutManager.VERTICAL,
                false
            )

            this?.setHasFixedSize(true)
        }


        viewModel.response.observe(this, { listTvShows ->

            tvShowAdapter.movies = listTvShows

        })


    }


}