package com.example.themovies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.themovies.databinding.ItemMovieBinding
import com.example.themovies.model.Movie
import com.example.themovies.utils.AppConstants.IMAGE_BASE

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


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

        holder.binding.apply {
            movieTitle.text = currentMovies.name
            movieReleaseDate.text = currentMovies.releaseDate
//            Glide.with().load(IMAGE_BASE + currentMovies.poster).into(moviePoster)
            moviePoster.load(currentMovies.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }


}