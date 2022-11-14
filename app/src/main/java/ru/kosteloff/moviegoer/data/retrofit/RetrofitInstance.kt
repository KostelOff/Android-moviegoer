package ru.kosteloff.moviegoer.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kosteloff.moviegoer.data.retrofit.api.ApiService

const val BASE_URL = "https://api.themoviedb.org"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}