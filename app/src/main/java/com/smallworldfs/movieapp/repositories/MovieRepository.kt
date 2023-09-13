package com.smallworldfs.movieapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smallworldfs.movieapp.model.Movie
import com.smallworldfs.movieapp.model.MovieListResponse
import com.smallworldfs.movieapp.storage.DataPersistence
import com.smallworldfs.movieapp.viewmodel.Event
import com.smallworldfs.movieapp.viewmodel.Resource
import com.smallworldfs.movieapp.webservice.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(val apiService: APIService) {

     fun getMovies(): MutableLiveData<Resource<ArrayList<Movie>?>> {

         val movieListLiveData: MutableLiveData<Resource<ArrayList<Movie>?>> = MutableLiveData()
         movieListLiveData.value = Resource.success(DataPersistence.loadMoviesList())
         apiService.getMovies(APIConstants.TOKEN).enqueue( object : Callback<MovieListResponse> {

             override fun onResponse(
                 call: Call<MovieListResponse>,
                 response: Response<MovieListResponse>
             ) {
                 if (response.body()!=null) {
                     val movies = response.body()?.results as ArrayList
                     DataPersistence.saveMoviesList(movies)
                     movieListLiveData.value= Resource.success(movies)
                 }
             }

             override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                 movieListLiveData.value= Resource.error(t.toString(),null)
             }
         })
         return movieListLiveData
     }

    fun getMovieDetail(id :  String): MutableLiveData<Resource<Movie?>> {

        val movieListLiveData: MutableLiveData<Resource<Movie?>> = MutableLiveData()
        //movieListLiveData.value = Resource.success(DataPersistence.loadMoviesList())
        apiService.getMovie(APIConstants.TOKEN,id).enqueue( object : Callback<Movie> {

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.body()!=null) {
                    val movie = response.body() as Movie
                    //DataPersistence.saveMoviesList(movie)
                    movieListLiveData.value= Resource.success(movie)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieListLiveData.value= Resource.error(t.toString(),null)
            }
        })
        return movieListLiveData
    }
}