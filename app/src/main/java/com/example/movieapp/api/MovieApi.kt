package com.example.movieapp.api

import com.example.movieapp.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("/1.json")
    //suspend fun getMovies(): Call<Movie>
    fun getMovies(): Call<MovieResponse>

    @GET("/1.json")
    fun getMovies1(): Callback<List<Movie>>

    @GET("/posts")
    fun getData(): Call<List<MyDataItem>>

    @GET("/1.json")
    fun getMovieList(): MovieResponse

}