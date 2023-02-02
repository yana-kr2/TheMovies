package com.example.themovies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.themovies.databinding.ItemMovieBinding
import com.example.themovies.model.movie.Movie
import com.example.themovies.utils.showToast
import javax.inject.Inject


typealias OnMovieClick = (movie: Movie) -> Unit

class MovieAdapter @Inject constructor() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var onMovieClick: OnMovieClick? = null

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
        val id = currentMovies.id

        holder.binding.apply {
//            val text = currentMovies.summary
//            overview.text = Html.fromHtml(text)
//            overview.movementMethod = ScrollingMovementMethod()
//            Glide.with().load(IMAGE_BASE + currentMovies.poster).into(moviePoster)
            movieTitle.text = currentMovies.name
            moviePoster.load(currentMovies.image?.original) {
                crossfade(true)
                crossfade(1000)
            }
            containerView.setOnClickListener {
                val movie = Movie(
                    currentMovies.id,
                    currentMovies.image,
                    currentMovies.name,
                    currentMovies.summary,
                    currentMovies.premiered,
                    currentMovies.ended
                )
                onMovieClick?.invoke(movie)
            }
        }
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


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