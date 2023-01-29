package com.example.themovies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.databinding.ItemMovieBinding
import com.example.themovies.model.Movie
import com.example.themovies.utils.IMAGE_BASE

class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(v)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
    }

    override fun getItemCount(): Int = movies.size


    inner class MovieViewHolder(item: View) :
        RecyclerView.ViewHolder(item) {

        private val binding = ItemMovieBinding.bind(item)

        fun bind(movie: Movie?) {
            binding.movieTitle.text = movie?.title
            binding.movieReleaseDate.text = movie?.releaseDate
            Glide.with(itemView).load(IMAGE_BASE + movie?.poster).into(binding.moviePoster)

        }

    }
}













