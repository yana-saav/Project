package com.example.project.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project.data.remote.model.MovieItem


@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieItem: MovieItem)

    @Delete
    suspend fun delete(movieItem: MovieItem)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List< MovieItem>>

}