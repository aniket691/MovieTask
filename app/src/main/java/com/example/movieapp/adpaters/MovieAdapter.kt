package com.example.movieapp.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.Movie
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.ui.home.HomeFragment
import com.example.movieapp.viewmodels.MovieDatabaseViewModel


class MovieAdapter(val context: Context, val viewModel: MovieDatabaseViewModel) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    //movie list which will hold the movies
    private var movieList = emptyList<Movie>()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //getting the view to be rendered
        val mvTitle: TextView = itemView.findViewById(R.id.mvTitle)
        val mvYear: TextView = itemView.findViewById(R.id.mvYear)
        val mvRunTime: TextView = itemView.findViewById(R.id.mvRunTime)
        val mvCast: TextView = itemView.findViewById(R.id.mvCast)
        val imgView: ImageView = itemView.findViewById(R.id.thumbnail)
        val btnFav: ImageButton = itemView.findViewById(R.id.buttonFav)
        val btnDel: ImageButton = itemView.findViewById(R.id.buttonDel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        //get xm file of the rows of recyclerview
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_article_previews,
            parent, false
        )
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        //returns size of movies
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //binding the data with views
        holder.mvTitle.text = movieList[position].Title
        holder.mvRunTime.text = movieList[position].Runtime
        holder.mvYear.text = movieList[position].Year
        holder.mvCast.text = movieList[position].Cast

        //setting the images to the views
        Glide
            .with(context)
            .load(movieList[position].moviePoster)
            .centerCrop()
            .placeholder(R.drawable.profile_pic)
            .into(holder.imgView);

        holder.btnDel.setOnClickListener(View.OnClickListener { view ->
            viewModel.deleteData(movieList[position])
            Toast.makeText(
                context,
                "Deleted",
                Toast.LENGTH_SHORT
            ).show();
        })

        holder.btnFav.setOnClickListener(View.OnClickListener { view ->
            Toast.makeText(
                context,
                "Fav Clicked",
                Toast.LENGTH_SHORT
            ).show();
        })
    }

    //notifying data set change to the list
    fun setData(movie: List<Movie>) {
        this.movieList = movie
        notifyDataSetChanged()
    }

}






