package com.smallworldfs.movieapp.di

import com.smallworldfs.movieapp.repositories.MovieRepository
import com.smallworldfs.movieapp.webservice.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun provideApiService(): APIService {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIService::class.java)
    }

    @Provides
    fun provideMovieRepository(apiService: APIService):MovieRepository{
        return MovieRepository(apiService)
    }

}