package com.example.project.data.local

import androidx.lifecycle.LiveData
import com.example.project.data.remote.model.MovieItem

interface MoviesRepository {
    val allMovies: LiveData<List<MovieItem>>
    suspend fun insertMovie(movieItemEntity: MovieItem, onSuccess:() -> Unit)
    suspend fun deleteMovie(movieItemEntity: MovieItem, onSuccess:() -> Unit)

}