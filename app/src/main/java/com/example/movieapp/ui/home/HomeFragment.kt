package com.example.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.movieapp.adpaters.MovieAdapter
import com.example.movieapp.api.MovieApi
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.db.MovieDao
import com.example.movieapp.db.MovieDatabase
import com.example.movieapp.models.Movie
import com.example.movieapp.util.Constants.Companion.BASE_URL
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //viewModel
    //lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieApi::class.java)

        //first
//        val retrofitData = retrofitBuilder.getMovies()
//
//        retrofitData.enqueue(object : Callback<MovieList?> {
//            override fun onResponse(call: Call<MovieList?>, response: Response<MovieList?>) {
//                val responseBody = response.body()!!
//
//                Toast.makeText(context, responseBody.toString(), Toast.LENGTH_LONG).show()
//            }
//
//            override fun onFailure(call: Call<MovieList?>, t: Throwable) {
//                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
//            }
//        })

        var movieList: ArrayList<Movie> = ArrayList()

        var m1 = Movie(
            "1", "Genre1",
            "Director1", "genree1", "mp1",
            "rat1", "rt1", "shortSum1",
            "summ1", "Titl1", "Write1", "year1", "yt1"
        )


        var m2 = Movie(
            "2", "Genre2",
            "Director2", "genree2", "mp2",
            "rat2", "rt2", "shortSum2",
            "summ2", "Titl2", "Write2", "year2", "yt2"
        )

        movieList.add(m1)
        movieList.add(m2)

        val db = context?.let {
            Room.databaseBuilder(
                it,
                MovieDatabase::class.java, "movie-atabase"
            ).build()
        }


        val movieDao = db?.getMovieDao()

        var c: Long =  1L
        for(movie in movieList) {
            GlobalScope.launch {
                if (movieDao != null) {
                    c = movieDao.upsert(movie)
                }
            }
        }

        Toast.makeText(context, c.toString(), Toast.LENGTH_LONG).show()

        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )


        binding.recyclerView.adapter = context?.let { MovieAdapter(it, movieList) }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}