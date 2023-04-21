package com.example.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
import com.example.movieapp.viewmodels.MovieDatabaseViewModel
import com.example.movieapp.viewmodels.MovieViewModel
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

        //Data from retrofit
        var movieList: ArrayList<Movie> = ArrayList()
        var m1 = Movie(
            "tt8144778",
            "Dave Castro|Justin Bergh",
            "Heber Cannon",
            "Documentary",
            "https://fastly.picsum.photos/id/559/1280/720.jpg?hmac=5PsS2cP3yBSt00WIAylUosxRT4h4S9BfWw42BdcPZlY",
            "7.1",
            "119",
            "n 2017 the fittest athletes on Earth took on the unknown and unknowable during four of the most intense days of competition in CrossFit Games history. &quot;The Redeemed and the Dominant: ...",
            "In 2017 the fittest athletes on Earth took on the unknown and unknowable during four of the most intense days of competition in CrossFit Games history. \\\"The Redeemed and the Dominant: Fittest on Earth \\\" captures all the drama as top athletes resembling chiseled Grecian gods descend on Madison, Wisconsin, to face a series of trials. Hercules faced 12; they take on 13. Emotions run high as a throng of Australian athletes rise to the top. By the end of the competition, some learn tough lessons-that all that glitters isn't gold, or even bronze-and some learn that they're even stronger than they realized. The best among them enter the pantheon of CrossFit giants and earn the right to call themselves the \\\"Fittest on Earth.\\\"\"",
            "Titl1",
            "Write1",
            "year1",
            "yt1"
        )

        var m2 = Movie(
            "2", "Genre2",
            "Director2", "genree2", "https://picsum.photos/1280/720",
            "rat2", "rt2", "shortSum2",
            "summ2", "Titl2", "Write2", "year2", "yt2"
        )

        //viewModel
        movieViewModel = ViewModelProvider(this).get(MovieDatabaseViewModel::class.java)

        //add to database
        movieViewModel.addMovie(m1)
        movieViewModel.addMovie(m2)

        //fetch from database
        val adapter = MovieAdapter(movieViewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        movieViewModel.getReadAllData().observe(viewLifecycleOwner, Observer { movie ->
            adapter.setData(movie)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}