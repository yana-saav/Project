package com.example.project.presentation.first

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.REALIZATION
import com.example.project.data.local.MoviesDatabase
import com.example.project.data.local.MoviesRepositoryRealization
import com.example.project.data.remote.RemoteRepository
import com.example.project.data.remote.model.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class FirstFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RemoteRepository()
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()
    val context = application
    fun getMoviesRetrofit() {
        viewModelScope.launch {
            myMovies.value = repository.getMovies()
        }
    }

    fun initDatabase() {
        val daoMovie = MoviesDatabase.getInstance(context).getMoviesDao()
        REALIZATION = MoviesRepositoryRealization(daoMovie)
    }
}