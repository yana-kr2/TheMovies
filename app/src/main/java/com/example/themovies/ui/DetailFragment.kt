package com.example.themovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themovies.R
import com.example.themovies.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment() {


    companion object {
        const val TAG = "DetailFragment"
    }

    private var mBinding: FragmentDetailBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root
        mBinding = binding

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun setupView() {
        TODO("Not yet implemented")
    }


}