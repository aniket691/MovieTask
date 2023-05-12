package com.example.movieapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.db.MovieDatabase
import com.example.movieapp.models.Movie
import com.example.movieapp.repository.MovieDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<Movie>>
    private val databaseRepository: MovieDatabaseRepository

    init {
        val movieDao = MovieDatabase(application).getMovieDao()
        databaseRepository = MovieDatabaseRepository((movieDao))
        readAllData = databaseRepository.readAllData
    }

    fun addMovie(movieList: List<Movie>) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertMovie(movieList)
        }
    }

    fun getReadAllData(): LiveData<List<Movie>> {
        return readAllData
    }

    fun deleteData(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.deleteMovie(movie)
        }
    }

}