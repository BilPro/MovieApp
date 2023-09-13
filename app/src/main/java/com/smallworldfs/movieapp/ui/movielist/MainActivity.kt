package com.smallworldfs.movieapp.ui.movielist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.smallworldfs.movieapp.R
import com.smallworldfs.movieapp.model.Movie
import com.smallworldfs.movieapp.ui.moviedetail.MovieDetailActivity
import com.smallworldfs.movieapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()
    private var movies = ArrayList<Movie>()
    lateinit var adapter : MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvNoData  = findViewById<TextView>(R.id.tvNoData)
        val rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
         adapter = MovieListAdapter(arrayListOf<Movie>()){ position, movie ->
            val intent = Intent(this,MovieDetailActivity::class.java)
            val b = Bundle()
            b.putLong("movieId", movie.id)

            intent.putExtras(b)

            startActivity(intent)
        }
        rvMovies.adapter = adapter
        viewModel.getMovies().observe(this, Observer {
            //Log.d("Movie List","Movie Size "+ (it.data?.size?:0))
            it.data?.let { it1 ->
                adapter.addItems(it1)
                movies = it1
                tvNoData.visibility= View.GONE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater

        inflater.inflate(R.menu.search_menu, menu)

        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        val searchView: SearchView = searchItem.getActionView() as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    filter(p0)
                }
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<Movie> = ArrayList()

        viewModel.filterMoviesByName(text, filteredlist,movies)
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Movie Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredlist)
        }
    }


}