package com.example.themovies.ui

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.themovies.R
import com.example.themovies.adapters.CastAdapter
import com.example.themovies.databinding.FragmentDetailBinding
import com.example.themovies.utils.AppConstants
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
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        castAdapter = CastAdapter()
        id = arguments?.getInt("id") ?: -1
        url = arguments?.getString("officialSite")
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCastRv()
        subscribeUi()
//        setupClickListener()
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
        val overview = arguments?.getString("summary")
        mBinding?.apply {
            premieredDate.text = arguments?.getString("premiered")?.replace("-", ".")
            moviePoster.load(AppConstants.IMAGE_BASE_URL + (arguments?.getString("poster")))
            movieTitle.text = arguments?.getString("title")
            description.text = overview
        }
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

//    private fun setupClickListener() {
//        mBinding?.officialSite?.setOnClickListener {
//            val bundle = bundleOf(
//                "officialSite" to url
//            )
//            Navigation.findNavController(it)
//                .navigate(R.id.action_detail_fragment_to_webViewFragment, bundle)
//        }
//    }
}






