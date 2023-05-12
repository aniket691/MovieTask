package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import com.example.movieapp.db.MovieDao
import com.example.movieapp.models.Movie

class MovieDatabaseRepository(private val movieDao: MovieDao) {

    //creating repository function for getting on movies
    val readAllData: LiveData<List<Movie>> = movieDao.getAllMovies()

    //function for inserting movie into the database
    suspend fun insertMovie(movieList: List<Movie>) {
        movieDao.upsert(movieList)
    }

    //function for deleting movie from the database
    suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }


}