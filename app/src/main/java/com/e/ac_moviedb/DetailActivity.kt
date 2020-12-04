package com.e.ac_moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.e.ac_moviedb.Model.Movie
import com.e.ac_moviedb.Model.MovieDbResults
import com.e.ac_moviedb.PruebasCurso.MediaProvider
import com.e.ac_moviedb.databinding.ActivityDetailBinding
import com.e.ac_moviedb.databinding.ActivityMainBinding.inflate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_MOVIE = "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if(movie != null) {
            renderMovie(binding, movie)
        }
    }

    fun renderMovie(binding: ActivityDetailBinding, movie: Movie) {
        setTitle(movie.title)
        binding.detailOverview.text = movie.overview
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.backdropPath}")
                .centerCrop()
                .into(binding.detailImage)
        binding.detailOtherinfo.text = buildSpannedString {
            bold { append("Original Language: ") }
            appendLine(movie.originalLanguage)

            bold { append("Relase date: ") }
            appendLine(movie.releaseDate)

            bold { append("Popularity: ") }
            appendLine(movie.popularity.toString())

            bold { append("Adult: ") }
            appendLine(movie.adult.toString())

            bold { append("Vote average: ") }
            appendLine(movie.voteAverage.toString())
        }
    }
}