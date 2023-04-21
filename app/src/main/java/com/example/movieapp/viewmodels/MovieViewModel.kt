package com.example.movieapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.MovieResponse
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(
    val movieRepository: MovieRepository
) : ViewModel() {

    val movie: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var moviePage = 1

    fun getMovie() = viewModelScope.launch {
//        movie.postValue(Resource.Loading())
//        val response = movieRepository.getMoviesRep()
//        movie.postValue(handleMovieResponse(response))

    }

    private fun handleMovieResponse(response: Response<MovieResponse>): Resource<MovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { movieResponse ->
                return Resource.Success(movieResponse)
            }
        }
        return Resource.Error(response.message())
    }

}