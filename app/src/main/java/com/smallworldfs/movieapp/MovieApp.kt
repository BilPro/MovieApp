package com.smallworldfs.movieapp

import android.app.Application
import android.content.Context
import com.smallworldfs.movieapp.storage.DataPersistence
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DataPersistence.setUp(this)
    }


}