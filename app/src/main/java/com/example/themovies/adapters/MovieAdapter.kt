package com.example.themovies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.themovies.R
import com.example.themovies.databinding.ItemMovieBinding
import com.example.themovies.model.tmdb.Movie
import com.example.themovies.utils.AppConstants


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = movies.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovies = movies[position]
        val bundle = bundleOf(
            "title" to currentMovies.title,
            "poster" to currentMovies.posterPath,
            "summary" to currentMovies.overview,
            "premiered" to currentMovies.releaseDate,
            "id" to currentMovies.id,
        )

        holder.binding.apply {
            movieTitle.text = currentMovies.title
            moviePoster.load(AppConstants.IMAGE_BASE_URL + currentMovies.posterPath) {
                crossfade(true)
                crossfade(1000)
            }
            containerView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_mainFragment_to_detail_fragment, bundle)
            }
        }
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movies: List<Movie>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
}