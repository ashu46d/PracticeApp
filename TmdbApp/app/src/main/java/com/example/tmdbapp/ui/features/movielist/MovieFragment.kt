package com.example.tmdbapp.ui.features.movielist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.*
import com.example.tmdbapp.data.networking.models.response.MovieResults
import com.example.tmdbapp.databinding.FragmentMovieBinding
import com.example.tmdbapp.ui.features.moviedetails.MovieDetailFragment
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieFragment : Fragment() {


    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(
            MovieViewModel::class.java
        )
    }

    lateinit var binding: FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mViewModel.getMovies().observe(viewLifecycleOwner, Observer {
            it.enqueue(object : Callback<MovieResults> {
                override fun onFailure(call: Call<MovieResults>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<MovieResults>,
                    response: Response<MovieResults>
                ) {
                    val results = response.body()
                    val listOfMovies = results!!.getResults()

                    setAdapter(listOfMovies)
                }

            })

        })

    }

    fun setAdapter(listOfMovies: List<MovieResults.Result?>?) {
        val adapter = MoviesAdapter(listOfMovies)
        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.movieRecyclerView.adapter = adapter
    }


}