package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.db.MovieDao
import com.example.movieapp.models.Movie

class MovieDatabaseRepository(private val movieDao: MovieDao) {

    val readAllData: LiveData<List<Movie>> = movieDao.getAllMovies()

    suspend fun insertMovie(movie: Movie) {
        movieDao.upsert(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }


}