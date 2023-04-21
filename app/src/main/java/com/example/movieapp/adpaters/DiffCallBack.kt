package com.example.movieapp.adpaters

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.models.Movie

class DiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        //check items on the basis of id
        return oldItem.IMDBID == newItem.IMDBID
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        //check if objects are same
       return  oldItem == newItem
    }

}