package com.example.themovies.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.themovies.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        mBinding?.movieTitle?.text = arguments?.getString("title")
        mBinding?.moviePoster?.load((arguments?.getString("poster")))
        val description = arguments?.getString("summary")
        mBinding?.description?.text = Html.fromHtml(description)

    }
}


