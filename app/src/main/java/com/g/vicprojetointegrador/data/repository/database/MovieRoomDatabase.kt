package com.g.vicprojetointegrador.data.repository.database

import android.content.Context
import androidx.room.Room

object MovieRoomDatabase {
    private var database: MovieDatabase? = null

    fun getInstance(context: Context): MovieDatabase {
        val localDatabase = database
            // If database exists...
        return if (localDatabase != null) {
            localDatabase
        } else synchronized(this) {
            // If database does not exist...
            buildDatabase(context).also { database = it }
        }
    }

    private fun buildDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie-db")
            .build()
    }
}