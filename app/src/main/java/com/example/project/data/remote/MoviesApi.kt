package com.example.project.data.remote

import com.example.project.data.remote.model.MoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {
    @GET("3/movie/popular?api_key=da2be817b22919b16f1c99c901175d18&language=en-US&page=1")
    suspend fun getPopularMovie(): Response<MoviesModel>
}