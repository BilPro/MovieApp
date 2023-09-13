package com.smallworldfs.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smallworldfs.movieapp.model.Movie
import com.smallworldfs.movieapp.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor (val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(): MutableLiveData<Resource<ArrayList<Movie>?>> {
        return movieRepository.getMovies()
    }

    fun getMovie(id:String): MutableLiveData<Resource<Movie?>> {
        return movieRepository.getMovieDetail(id)
    }

    fun filterMoviesByName(
        text: String,
        filteredlist: ArrayList<Movie>,
        movies : ArrayList<Movie>
    ) {
        for (movie in movies) {
            if (movie.title.lowercase().contains(text.lowercase())) {
                filteredlist.add(movie)
            }
        }
    }
}