package com.example.movieapp.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.models.Movie
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.ui.home.HomeFragment


class MovieAdapter(val requiredContext: Context, val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mvTitle: TextView = itemView.findViewById(R.id.mvTitle)
        val mvYear: TextView = itemView.findViewById(R.id.mvYear)
        val mvRunTime: TextView = itemView.findViewById(R.id.mvRunTime)
        val mvCast: TextView = itemView.findViewById(R.id.mvCast)
        val btnFav: TextView = itemView.findViewById(R.id.buttonFav)
        val btnDel: TextView = itemView.findViewById(R.id.buttonDel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_article_previews,
            parent, false
        )


        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.mvTitle.text = movieList[position].Title
        holder.mvRunTime.text = movieList[position].Runtime
        holder.mvYear.text = movieList[position].Year
        holder.mvCast.text = movieList[position].Cast

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(movieList[position]) }
        }

        holder.btnDel.setOnClickListener(View.OnClickListener { view ->
            Toast.makeText(
                requiredContext,
                "Del Clicked",
                Toast.LENGTH_SHORT
            ).show();
        })

        holder.btnFav.setOnClickListener(View.OnClickListener { view ->
            Toast.makeText(
                requiredContext,
                "Fav Clicked",
                Toast.LENGTH_SHORT
            ).show();
        })
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }
}






