package com.g.vicprojetointegrador.utils

import android.app.Application
import com.g.vicprojetointegrador.data.repository.database.MovieRoomDatabase

class MoviesApplication : Application() {
    // Using by lazy so the database and the repository are only created when they are needed
    // rather than when the application starts
    val database by lazy { MovieRoomDatabase.getDatabase(this) }
}