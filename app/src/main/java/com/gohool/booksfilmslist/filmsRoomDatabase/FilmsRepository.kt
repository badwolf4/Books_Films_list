package com.gohool.booksfilmslist.filmsRoomDatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class FilmsRepository(application: Application) {
    private var filmsDao : FilmsDao

    init {
        Log.d("FilmRepo: ", "Initializing database")
        val database = FilmsDatabase.getInstance(application.applicationContext)
        Log.d("FilmRepo: ", "Database initialized")
        filmsDao = database!!.filmsDao()
        Log.d("FilmRepo: ", "Dao initialized")
    }

    fun insertFilm(film: Film){
        Log.d("RoomLog: ", "Initializing coroutine ")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("RoomLog: ", "Inside coroutine ")
            filmsDao.insert(film)
            Log.d("RoomLog: ", "After insertion ")
        }

        Log.d("RoomLog: ", "Coroutine initialized ")
    }

    fun upDateFilm(film : Film){
        CoroutineScope(Dispatchers.IO).launch {
            filmsDao.update(film)
        }
    }

    fun deleteFilm(film: Film){
        CoroutineScope(Dispatchers.IO).launch {
            filmsDao.delete(film)
        }
    }

    fun getAllFilmsAsync():Deferred<LiveData<List<Film>>> =
        CoroutineScope(Dispatchers.IO).async {
            filmsDao.getAll()
    }
}