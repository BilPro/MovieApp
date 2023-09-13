package com.smallworldfs.movieapp.webservice

import com.smallworldfs.movieapp.model.Movie
import com.smallworldfs.movieapp.model.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIService {

    @Headers("accept: application/json")
    @GET("popular?language=en-US&page=1")
    fun getMovies(@Header("Authorization") authHeader:String): Call<MovieListResponse>

    @Headers("accept: application/json")
    @GET("{id}?language=en-US")
    fun getMovie(@Header("Authorization") authHeader:String, @Path("id") id: String): Call<Movie>

}