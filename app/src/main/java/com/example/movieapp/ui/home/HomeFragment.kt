package com.example.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adpaters.MovieAdapter
import com.example.movieapp.api.MovieApi
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.db.MovieDatabase
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieResponse
import com.example.movieapp.util.Constants.Companion.BASE_URL
import com.example.movieapp.viewmodels.MovieDatabaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    private lateinit var movieViewModel: MovieDatabaseViewModel

    //adapter
    lateinit var movieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        //creating binding instance for fragment
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

        //placeholder Data for retrofit
        var movieList: ArrayList<Movie> = ArrayList()


        //get data
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {

            val response = retrofit.getMovies()
            var mv: List<Movie> = ArrayList()

            val movieDatabase: MovieDatabase

            response.enqueue(object : Callback<MovieResponse?> {
                override fun onResponse(
                    call: Call<MovieResponse?>,
                    response: Response<MovieResponse?>
                ) {
                    if (response.isSuccessful) {
                        val movieObj = response.body();
                        if (movieObj != null)
                            mv = movieObj.movieList
                    }
                    movieViewModel.addMovie(mv)

                }

                override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()

                }
            })

        }


        //viewModel
        movieViewModel = ViewModelProvider(this).get(MovieDatabaseViewModel::class.java)

        //adding data to database
//        movieViewModel.addMovie(m1)
//        movieViewModel.addMovie(m2)

        //fetch from database
        val adapter = MovieAdapter(requireContext(), movieViewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

        //setting live data to the adapter
        movieViewModel.getReadAllData().observe(viewLifecycleOwner, Observer { movie ->
            adapter.setData(movie)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        //setting binding null on destroy
        _binding = null
    }
}