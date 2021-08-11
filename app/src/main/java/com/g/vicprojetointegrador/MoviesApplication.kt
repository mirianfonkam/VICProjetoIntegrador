package com.g.vicprojetointegrador

import android.app.Application
import com.g.vicprojetointegrador.data.repository.database.MovieRoomDatabase

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MovieRoomDatabase.getDatabase(this)
    }
}