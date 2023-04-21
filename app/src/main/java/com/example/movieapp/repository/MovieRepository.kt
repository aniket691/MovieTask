package com.example.movieapp.repository

import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.db.MovieDatabase

class MovieRepository(
    val db: MovieDatabase
) {
        suspend fun getMoviesRep() = RetrofitInstance.api.getMovies()

}