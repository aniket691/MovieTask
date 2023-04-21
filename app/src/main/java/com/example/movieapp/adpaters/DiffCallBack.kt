package com.example.movieapp.adpaters

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.models.Movie

class DiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.IMDBID == newItem.IMDBID
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
       return  oldItem == newItem
    }

}