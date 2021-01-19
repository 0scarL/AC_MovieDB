package com.e.ac_moviedb.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.e.ac_moviedb.Model.Movie
import com.e.ac_moviedb.R
import kotlin.properties.Delegates

/**esta es la interfaz reemplazada por la lamda**/
//interface MovieListener{   //tipo Input - > Unit
//    fun getMovie(movie: Movie)
//}

class MovieAdapter( listMovies: List<Movie> = listOf(), private val movieListener: (movie: Movie)->Unit) : RecyclerView.Adapter<MovieVH>() {

    var listMovies : List<Movie> by Delegates.observable(listMovies){_, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback(){
            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].id == new[newItemPosition].id

        }).dispatchUpdatesTo(this)
    }

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