package com.smallworldfs.movieapp.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.smallworldfs.movieapp.R
import com.smallworldfs.movieapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val tvTitle =  findViewById<TextView>(R.id.tvTitle)
        val tvStatus =  findViewById<TextView>(R.id.tvStatus)
        val tvTagline =  findViewById<TextView>(R.id.tvTagline)
        val tvVoteAverage =  findViewById<TextView>(R.id.tvVoteAverage)
        val tvOverview =  findViewById<TextView>(R.id.tvOverview)

        val b = intent.extras
        var value = -1L // or other values

        if (b != null) {
            value = b.getLong("movieId")
            viewModel.getMovie(value.toString()).observe(this, Observer {
                Log.d("Movie detail", "Movie  " + (it.data?.title ?: ""))
                //tvMovieName.text = it.data?.get(0)?.title +" "+ it.data?.get(0)?.release_date.
                tvTitle.text = it.data?.title
                tvStatus.text = "Status ${it.data?.status}"
                tvTagline.text = "Tag Line ${it.data?.tagline}"
                tvVoteAverage.text = "Vote ${it.data?.vote_average}"
                tvOverview.text =it.data?.overview
            })
        }
    }
}