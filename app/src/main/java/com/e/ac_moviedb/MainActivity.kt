package com.e.ac_moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import com.e.ac_moviedb.Adapter.MovieAdapter
import com.e.ac_moviedb.Model.MovieDbClientRetrofitService
import com.e.ac_moviedb.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var movieAdapter : MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        onEfect(binding)


        movieAdapter = MovieAdapter(
                emptyList(), { Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show() } )

        binding.myRecyclerView.adapter = movieAdapter

        /**uso de libreria standar de Android, no es lo mas optimo para realizar la peticion en otro hilo**/
        thread{
            val apikey = getString(R.string.api_key)
            /**puedo crear una variable que contenga: la peticion del servicio usando el objeto Retrofit Service creado
            * al cual le solicito el Query por medio de la funcion creada en la interfaz, y le paso el key**/
            val popularMovies = MovieDbClientRetrofitService.service.listPopupularMovies(apikey)
            /**ahora le pido ejecutar el query y me devuelva el cuerpo de la solicitud**/
            val body = popularMovies.execute().body()

            /**por medio de este metodo tengo acceso al hilo principal**/
            runOnUiThread { if (body != null) {
                Toast.makeText(this, "Datos Cargados", Toast.LENGTH_SHORT).show()
                movieAdapter!!.listMovies = body.results
                movieAdapter!!.notifyDataSetChanged()
                offEfect(binding)

                }
            }
        }

    }

    fun onEfect(binding: ActivityMainBinding) {
        binding.progressBar.visibility = View.VISIBLE
    }
    fun offEfect(binding: ActivityMainBinding) {
        binding.progressBar.visibility = View.INVISIBLE
    }

}