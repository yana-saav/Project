package com.example.project.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.REALIZATION
import com.example.project.data.remote.model.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragmentViewModel: ViewModel() {
    fun insert(movieItem: MovieItem, onSuccess:() ->Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.insertMovie(movieItem){
                onSuccess()
            }
        }

    fun delete(movieItem: MovieItem, onSuccess:() ->Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.deleteMovie(movieItem){
                onSuccess()
            }
        }
}