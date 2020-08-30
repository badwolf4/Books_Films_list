package com.gohool.booksfilmslist.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gohool.booksfilmslist.filmsRoomDatabase.Film
import com.gohool.booksfilmslist.filmsRoomDatabase.FilmsRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class FilmsViewModel(application: Application) : AndroidViewModel(application) {
    private var filmsRepository : FilmsRepository = FilmsRepository(application)

    private var allFilms : Deferred<LiveData<List<Film>>> =
        filmsRepository.getAllFilmsAsync()

    fun insertFilm(film: Film){
        Log.d("RoomLog: ", "Go to film reposiotory ")
        filmsRepository.insertFilm(film)
        Log.d("RoomLog: ", "Back from film reposiotory ")
    }

    fun updateFilm(film : Film){
        filmsRepository.upDateFilm(film)
    }

    fun deleteFilm(film: Film){
        filmsRepository.deleteFilm(film)
    }

    fun getAllFilms() : LiveData<List<Film>> = runBlocking{
        allFilms.await()
    }

}