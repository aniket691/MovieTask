package com.example.movieapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "movies"
)
data class Movie(
    @PrimaryKey
    val IMDBID: String,
    val Cast: String,
    val Director: String,
    val Genres: String,
    val moviePoster: String,
    val Rating: String,
    val Runtime: String,
    val shortSummary: String,
    val Summary: String,
    val Title: String,
    val Writers: String,
    val Year: String,
    val youTubeTrailer: String
)