package com.e.ac_moviedb.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.ac_moviedb.Model.Movie
import com.e.ac_moviedb.databinding.LayoutMovieItemBinding

class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = LayoutMovieItemBinding.bind(itemView)
    fun bind (movie: Movie, movieListener: (movie: Movie)->Unit){
        binding.itemTitle.text = movie.title
        Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w780/${movie.posterPath}")
                .centerCrop()
                .into(binding.itemImage)
        itemView.setOnClickListener { movieListener(movie) }

    }
}