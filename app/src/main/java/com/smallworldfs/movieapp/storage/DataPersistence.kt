package com.smallworldfs.movieapp.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smallworldfs.movieapp.model.Movie
import java.lang.reflect.Type


object DataPersistence {

    lateinit var editor: SharedPreferences.Editor
    lateinit var sharedPreference: SharedPreferences

    fun setUp(applicationContext: Context) {
        sharedPreference =
            applicationContext.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        editor = sharedPreference.edit()

    }

    fun saveStringByKey(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    fun saveMoviesList(movies: ArrayList<Movie>) {
        val gson = Gson()
        val json = gson.toJson(movies)
        saveStringByKey("movieList", json)
    }

    fun loadMoviesList(): ArrayList<Movie>? {
        val gson = Gson()
        val json = getStringByKey("movieList")
        val typeMyType: Type = object : TypeToken<ArrayList<Movie?>?>() {}.type

        val movieList : ArrayList<Movie>?  = gson.fromJson(json, typeMyType) as ArrayList<Movie>?
        return movieList
    }

    fun getStringByKey(key: String): String? {
        return sharedPreference.getString(key, "")

    }


}