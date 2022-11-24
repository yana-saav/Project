package com.example.project.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project.data.remote.model.MovieItem

@Database(entities = [MovieItem::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        private var database:MoviesDatabase ?= null

        fun getInstance(context: Context): MoviesDatabase{
            val tempInstance = database
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()
                database = instance
                return instance
            }
        }

    }

}