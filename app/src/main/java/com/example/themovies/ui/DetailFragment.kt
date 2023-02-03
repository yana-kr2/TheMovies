package com.example.themovies.ui

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.themovies.adapters.CastAdapter
import com.example.themovies.databinding.FragmentDetailBinding
import com.example.themovies.utils.extensions.collectIn
import com.example.themovies.utils.extensions.showSnackBar
import com.example.themovies.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    companion object {
        const val TAG = "DetailFragment"
    }

    private var mBinding: FragmentDetailBinding? = null
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var castAdapter: CastAdapter
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        castAdapter = CastAdapter()
        id = arguments?.getInt("id") ?: -1
        // I know that is wrong
        viewModel.movieId = id as Int

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root
        mBinding = binding
        setupCastRv()
        subscribeUi()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    private fun subscribeUi() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.uiState.collectIn(viewLifecycleOwner) { uiState ->
                mBinding?.progressLoading?.root?.isVisible = uiState.isLoading
                uiState.errorMsg?.let {
                    Log.d(TAG, it)
                    activity?.showSnackBar(it)
                    viewModel.onErrorMessageShown()
                }
            }
        }
    }


    override fun setupView() {
        mBinding?.movieTitle?.text = arguments?.getString("title")
        mBinding?.moviePoster?.load((arguments?.getString("poster")))
        val description = arguments?.getString("summary")
        mBinding?.description?.text = Html.fromHtml(description)


    }

    private fun setupCastRv() {
        val manager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        mBinding?.rvCastList.apply {
            this?.adapter = castAdapter
            this?.layoutManager = manager
        }
        viewModel.list.observe(viewLifecycleOwner) {
            castAdapter.items = it
        }
        viewModel.getAllCast()
    }
}






