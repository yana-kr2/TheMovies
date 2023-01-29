package com.example.themovies.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themovies.adapters.MovieAdapter
import com.example.themovies.data.MovieApiInterface
import com.example.themovies.data.MovieApiService
import com.example.themovies.databinding.FragmentMainBinding
import com.example.themovies.model.Movie
import com.example.themovies.model.MovieResponse
import com.example.themovies.viewmodels.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : BaseFragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private var mBinding: FragmentMainBinding? = null
    private var movieList: RecyclerView? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        context ?: return binding.root
        mBinding = binding

        movieList = mBinding?.rvMoviesList
        movieList?.layoutManager = LinearLayoutManager(requireContext())
        movieList?.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            movieList?.adapter = MovieAdapter(movies)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {


            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun setupView() {

    }

}

