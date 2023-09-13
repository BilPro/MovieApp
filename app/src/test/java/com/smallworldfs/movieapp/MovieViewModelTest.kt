package com.smallworldfs.movieapp

import android.app.Application
import com.smallworldfs.movieapp.model.Movie
import com.smallworldfs.movieapp.repositories.MovieRepository
import com.smallworldfs.movieapp.viewmodel.MovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltViewModel
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        val movieRepository = mockk<MovieRepository>()
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun test_movie_search() {

        val movies = arrayListOf<Movie>()
        val moviesFiltered = arrayListOf<Movie>()
        movies.add(Movie(title = "Indiana Jones and the Dial of Destiny"))
        movies.add(Movie(title = "Fast X"))

        movieViewModel.filterMoviesByName("Dial",moviesFiltered,movies)

        assertEquals(moviesFiltered.size,1)
        Assert.assertNotEquals(moviesFiltered.size,2)
        assertEquals(moviesFiltered[0].title,"Indiana Jones and the Dial of Destiny")

    }
}