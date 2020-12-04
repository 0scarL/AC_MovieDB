package com.e.ac_moviedb.PruebasCurso

import com.e.ac_moviedb.Model.Movie

object MediaProvider {

    fun getItems(): List<Gaticos>{
        Thread.sleep(2000)
        return listOf(
                Gaticos("tigrillo", 10),
                Gaticos("siames", 7),
                Gaticos("rayito", 5)
        )
    }
}