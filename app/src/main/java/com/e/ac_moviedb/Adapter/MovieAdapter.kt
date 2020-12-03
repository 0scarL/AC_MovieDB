package com.e.ac_moviedb.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.ac_moviedb.Model.Movie
import com.e.ac_moviedb.R

/**esta es la interfaz reemplazada por la lamda**/
//interface MovieListener{   //tipo Input - > Unit
//    fun getMovie(movie: Movie)
//}

class MovieAdapter(var listMovies: List<Movie>, private val movieListener: (movie: Movie)->Unit) : RecyclerView.Adapter<MovieVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_movie_item,parent,false)
        return MovieVH(view)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(listMovies[position], movieListener)
    }


}