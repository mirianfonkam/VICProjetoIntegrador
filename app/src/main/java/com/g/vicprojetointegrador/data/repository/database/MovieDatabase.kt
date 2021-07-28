package com.g.vicprojetointegrador.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.g.vicprojetointegrador.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): FavoriteMovieDao
}