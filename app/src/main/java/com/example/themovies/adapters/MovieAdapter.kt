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
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val currentMovies = movies[position]
//        val bundle = bundleOf(
//            "title" to currentMovies.name,
//            "poster" to currentMovies.image.original,
//            "summary" to currentMovies.summary,
//            "premiered" to currentMovies.premiered,
//            "ended" to currentMovies.ended,
//            "id" to currentMovies.id,
//            "genres" to currentMovies.genres,
//            "premiered" to currentMovies.premiered,
//            "ended" to currentMovies.ended,
//            "officialSite" to currentMovies.officialSite
//        )

        holder.binding.apply {
            movieTitle.text = currentMovies.title
            moviePoster.load(IMAGE_BASE + currentMovies.posterPath) {
                crossfade(true)
                crossfade(1000)
            }
            containerView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_mainFragment_to_detail_fragment)
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