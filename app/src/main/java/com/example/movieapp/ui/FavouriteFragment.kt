package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adpaters.MovieAdapter
import com.example.movieapp.databinding.FragmentFavouriteBinding
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.home.HomeViewModel
import com.example.movieapp.viewmodels.MovieDatabaseViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteFragment : Fragment() {


    //binding instance
    private var _binding: FragmentFavouriteBinding? = null

    private val binding get() = _binding!!

    private lateinit var movieViewModel: MovieDatabaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //creating binding instance for accessing the view
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //creating view model
        movieViewModel = ViewModelProvider(this).get(MovieDatabaseViewModel::class.java)

        //fetching data from database
        val adapter = MovieAdapter(requireContext(),movieViewModel)

        binding.favouriteRecyclerView.adapter = adapter
        binding.favouriteRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        //setting livedata to the adapter
        movieViewModel.getReadAllData().observe(viewLifecycleOwner, Observer { movie ->
            adapter.setData(movie)
        })
    }

}