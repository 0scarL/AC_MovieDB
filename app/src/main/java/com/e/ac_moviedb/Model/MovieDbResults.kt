package com.e.ac_moviedb.Model

import com.google.gson.annotations.SerializedName

data class MovieDbResults(
    val page: Int,
    val results: List<MovieDb>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)