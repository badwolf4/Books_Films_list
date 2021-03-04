package com.gohool.booksfilmslist.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gohool.booksfilmslist.data.films.Film
import com.gohool.booksfilmslist.data.films.FilmsRepository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FilmsViewModel(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    val allFilms = filmsRepository.allFilms.asLiveData()

    fun insertFilm(film: Film) = viewModelScope.launch { filmsRepository.insertFilm(film)  }

    fun updateFilm(film : Film) = viewModelScope.launch { filmsRepository.upDateFilm(film) }

    fun deleteFilm(film: Film) = viewModelScope.launch { filmsRepository.deleteFilm(film) }
    fun getFilmById(filmId: Int) :LiveData<Film> =   filmsRepository.getFilmById(filmId).asLiveData()
    fun search(s : String) {

//        allFilms = LiveDataTransformations.switchMap(searchStringLiveData, string ->
//        repo.loadData(string)))
    }



}

class FilmsViewModelFactory(private  val repository: FilmsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FilmsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return FilmsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}