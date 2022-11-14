package ru.kosteloff.moviegoer.data.retrofit.repository

import retrofit2.Response
import ru.kosteloff.moviegoer.data.retrofit.RetrofitInstance
import ru.kosteloff.moviegoer.model.MovieModel

class RetrofitRepository {
    suspend fun getMovies(): Response<MovieModel> {
        return RetrofitInstance.apiService.getPopularMovie()
    }
}