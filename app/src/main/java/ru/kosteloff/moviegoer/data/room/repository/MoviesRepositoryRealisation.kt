package ru.kosteloff.moviegoer.data.room.repository

import androidx.lifecycle.LiveData
import ru.kosteloff.moviegoer.data.room.dao.MovieDao
import ru.kosteloff.moviegoer.model.Result

class MoviesRepositoryRealisation(private val movieDao: MovieDao) : MoviesRepository {
    override val allMovies: LiveData<List<Result>>
        get() = movieDao.getAllMovies()

    override suspend fun deleteMovie(movie: Result) {
        movieDao.delete(movie)
    }

    override suspend fun insertMovie(movie: Result) {
        movieDao.insert(movie)
    }
}