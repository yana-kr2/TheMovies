package com.example.themovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.themovies.databinding.FragmentMainBinding
import com.example.themovies.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

class MainFragment : BaseFragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private var mBinding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root
        mBinding = binding

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun setupView() {
    }

}