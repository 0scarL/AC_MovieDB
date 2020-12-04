package com.e.ac_moviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.e.ac_moviedb.Adapter.MovieAdapter
import com.e.ac_moviedb.Model.Movie
import com.e.ac_moviedb.Model.MovieDbClientRetrofit
import com.e.ac_moviedb.Model.MovieDbResults
import com.e.ac_moviedb.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private var movieAdapter : MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onEfect(binding)
        //sinCorrutina(binding)
        //conCorrutina(binding)
        conCorrutinaRetrofitOptimizado(binding)

        /**se pasa funcion lamda para detectar evento click**/
        movieAdapter = MovieAdapter(
                emptyList(), {
                goToDetailActivity(it) //si toca envia a otro activity
                Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show() } )
        binding.myRecyclerView.adapter = movieAdapter

    }

    private fun goToDetailActivity(movie: Movie) {
        val intentDetail = Intent(this, DetailActivity::class.java)
        intentDetail.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intentDetail)
    }

    ///////////////////////////////////////////////Con Corrutinas y optimizacion de interfaz metodos Retrofit////////////////////////////////////////
    fun conCorrutinaRetrofitOptimizado(binding: ActivityMainBinding) {
        lifecycleScope.launch {
            val apikey = getString(R.string.api_key)
            val popularMovies: MovieDbResults = MovieDbClientRetrofit.service.listPopupularMovies(apikey)
            Toast.makeText(this@MainActivity, "Datos Cargados", Toast.LENGTH_SHORT).show()
            movieAdapter!!.listMovies = popularMovies.results
            movieAdapter!!.notifyDataSetChanged()
            offEfect(binding)

        }
    }

////////////////////////////////////////////////Con Corrutinas lifeCycle y withContext, requiere interfaz retrofit sin optimizacion///////////////////////////////////////////////////////
//fun conCorrutina(binding: ActivityMainBinding) {
//
//    lifecycleScope.launch {
//        val apikey = getString(R.string.api_key)
//        val popularMovies = MovieDbClientRetrofit.service.listPopupularMovies(apikey)
//        val body = withContext(Dispatchers.IO){popularMovies.execute().body()} // withcontext es una funcion de suspension, permite escoger hilo, para el cuerpo de esa funcion
//        if (body != null) {
//            Toast.makeText(this@MainActivity, "Datos Cargados", Toast.LENGTH_SHORT).show()
//            movieAdapter!!.listMovies = body.results
//            movieAdapter!!.notifyDataSetChanged()
//            offEfect(binding)
//        }
//    }
//
//}


////////////////////////////////////////////////sin Corrutinas Libreria Standar Android//////////////////////////////////////////////////////
    /**uso de libreria standar de Android, no es lo mas optimo para realizar la peticion en otro hilo**/
//    fun sinCorrutina(binding: ActivityMainBinding) {
//
//        thread{
//            val apikey = getString(R.string.api_key)
//            /**puedo crear una variable que contenga: la peticion del servicio usando el objeto Retrofit Service creado
//             * al cual le solicito el Query por medio de la funcion creada en la interfaz, y le paso el key**/
//            val popularMovies = MovieDbClientRetrofit.service.listPopupularMovies(apikey)
//            /**ahora le pido ejecutar el query y me devuelva el cuerpo de la solicitud**/
//            val body = popularMovies.execute().body()
//
//            /**por medio de este metodo tengo acceso al hilo principal**/
//            runOnUiThread { if (body != null) {
//                Toast.makeText(this, "Datos Cargados", Toast.LENGTH_SHORT).show()
//                movieAdapter!!.listMovies = body.results
//                movieAdapter!!.notifyDataSetChanged()
//                offEfect(binding)
//
//                }
//            }
//        }
//    }


    fun onEfect(binding: ActivityMainBinding) {
        binding.progressBar.visibility = View.VISIBLE
    }
    fun offEfect(binding: ActivityMainBinding) {
        binding.progressBar.visibility = View.INVISIBLE
    }

}