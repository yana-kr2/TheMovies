package com.example.themovies.adapters

import android.util.Log
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

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = ItemMovieBinding.bind(view)

        val movieT = binding.movieTitle
        val movieD = binding.movieReleaseDate
        val movieP = binding.moviePoster
        fun bindMovie(movie : Movie){
            movieT.text = movie.title
            Log.i("MY LOG","title is ${movie.title} ")
            movieD.text = movie.releaseDate
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(movieP)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }
}













