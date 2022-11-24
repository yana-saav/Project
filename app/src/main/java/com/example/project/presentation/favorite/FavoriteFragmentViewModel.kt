package com.example.project.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.project.REALIZATION
import com.example.project.data.remote.model.MovieItem

class FavoriteFragmentViewModel: ViewModel() {
    fun getAllMovies(): LiveData<List<MovieItem>> {
        return REALIZATION.allMovies
    }
}