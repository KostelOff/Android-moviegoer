package ru.kosteloff.moviegoer.data.room.repository

import androidx.lifecycle.LiveData
import ru.kosteloff.moviegoer.model.Result

interface MoviesRepository {
    val allMovies: LiveData<List<Result>>
    suspend fun deleteMovie(movie: Result)
    suspend fun insertMovie(movie: Result)
}