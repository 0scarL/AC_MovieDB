package com.e.ac_moviedb.Model

import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApiService {

    @GET("movie/popular")
    fun listPopupularMovies (@Query("api_key")apiKey : String) : Call<MovieDbResults>

    @GET("movie/popular")
    fun listPopupularMoviesEspanol (@Query("api_key")apiKey : String, @Query("language")language: String) : Call<MovieDbResults>
}