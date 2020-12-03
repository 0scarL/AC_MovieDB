package com.e.ac_moviedb.Model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDbClientRetrofitService {
    private val MOVIES_URL = "https://api.themoviedb.org/3/"

    /** creamos un objeto retrofit al cual se le pasa la url base, el tipo de convertidor de json a class**/
    private val retrofit = Retrofit.Builder()
        .baseUrl(MOVIES_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    /**crea un servicio a partir de este objeto retrofit que se ha creado y le pasamos la interfaz con el endPoint creada**/
    val service = retrofit.create(MovieDbApiService::class.java)
}