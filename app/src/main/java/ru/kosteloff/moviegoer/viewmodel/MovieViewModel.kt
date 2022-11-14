package ru.kosteloff.moviegoer.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.kosteloff.moviegoer.data.retrofit.repository.RetrofitRepository
import ru.kosteloff.moviegoer.data.room.database.MoviesRoomDatabase
import ru.kosteloff.moviegoer.data.room.repository.MoviesRepositoryRealisation
import ru.kosteloff.moviegoer.model.MovieModel
import ru.kosteloff.moviegoer.model.Result

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    val mutableLiveData: MutableLiveData<Response<MovieModel>> = MutableLiveData()

    private val retrofitRepository: RetrofitRepository = RetrofitRepository()

    fun getMoviesRetrofit() {
        viewModelScope.launch {
            mutableLiveData.value = retrofitRepository.getMovies()
        }
    }

    val context = application
    lateinit var realisation: MoviesRepositoryRealisation

    fun initDatabase() {
        val daoMovie = MoviesRoomDatabase.getDataBase(context).getMovieDao()
        realisation = MoviesRepositoryRealisation(daoMovie)
    }

    fun getAllMovies(): LiveData<List<Result>> {
        return realisation.allMovies
    }

    fun insert(movie: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            realisation.insertMovie(movie)
        }
    }

    fun delete(movie: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            realisation.deleteMovie(movie)
        }
    }
}