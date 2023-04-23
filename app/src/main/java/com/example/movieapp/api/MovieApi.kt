package com.example.movieapp.api

import com.example.movieapp.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("/1.json")
    fun getMovies(): Call<MovieResponse>
//    fun getMovies(): Call<MovieResponse>
}