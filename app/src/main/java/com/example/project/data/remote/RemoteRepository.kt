package com.example.project.data.remote

import com.example.project.data.remote.model.MoviesModel
import retrofit2.Response

class RemoteRepository {
    suspend fun getMovies(): Response<MoviesModel>{
        return ApiFactory.api.getPopularMovie()
    }
}