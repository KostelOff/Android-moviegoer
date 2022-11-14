package ru.kosteloff.moviegoer.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kosteloff.moviegoer.data.room.dao.MovieDao
import ru.kosteloff.moviegoer.model.Result

@Database(entities = [Result::class], version = 1)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        private var dataBase: MoviesRoomDatabase? = null

        fun getDataBase(context: Context): MoviesRoomDatabase {
            return if (dataBase == null) {
                dataBase = Room.databaseBuilder(
                    context,
                    MoviesRoomDatabase::class.java,
                    "name_database"
                ).build()
                dataBase as MoviesRoomDatabase
            } else {
                dataBase as MoviesRoomDatabase
            }
        }
    }
}