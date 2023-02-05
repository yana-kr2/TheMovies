package com.example.themovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themovies.databinding.FragmentWebViewBinding

class WebViewFragment : BaseFragment() {

    companion object {
        const val TAG = "WebViewFragment"
    }

    private var mBinding: FragmentWebViewBinding? = null


    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWebViewBinding.inflate(inflater, container, false)
        context ?: return binding.root
        mBinding = binding
        return binding.root
    }

    override fun setupView() {
        arguments?.getString("officialSite")
            ?.let { mBinding?.webView?.loadUrlAndClearBackStack(it) }
    }

    override fun onPause() {
        super.onPause()
        mBinding?.webView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        mBinding?.webView?.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}