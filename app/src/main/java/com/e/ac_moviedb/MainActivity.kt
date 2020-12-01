package com.e.ac_moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.e.ac_moviedb.Model.MovieDbClientRetrofitService
import com.e.ac_moviedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Thread{
            val apiKey = getString(R.string.api_key)

            /**puedo crear una variable que contenga: la peticion del servicio usando el objeto Retrofit Service creado
             * al cual le solicito el Query por medio de la funcion creada en la interfaz, y le paso el key**/
             val popularMovies = MovieDbClientRetrofitService.service.listPopupularMovies(apiKey)
            /**ahora le pido ejecutar el query y me devuelva el cuerpo de la solicitud**/
             val body = popularMovies.execute().body()

            if (body != null) {
                    Log.d("MainActivity ", "bloque: ${body.results.size}")
                }
            else {
                Toast.makeText(this, "el mensaje no fue leido", Toast.LENGTH_SHORT).show()
            }

        }
    }
}