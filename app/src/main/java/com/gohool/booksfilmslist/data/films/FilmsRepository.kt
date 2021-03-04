package com.gohool.booksfilmslist.data.films

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.gohool.booksfilmslist.data.films.Film
import com.gohool.booksfilmslist.data.films.FilmsDao
import com.gohool.booksfilmslist.data.films.FilmsDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executor

class FilmsRepository(
    private val filmsDao : FilmsDao
) {

    val allFilms: Flow<List<Film>> = filmsDao.getAll()

    @WorkerThread
    suspend fun insertFilm(film: Film) = filmsDao.insert(film)

    @WorkerThread
    suspend fun upDateFilm(film : Film) = filmsDao.update(film)

    @WorkerThread
    suspend fun deleteFilm(film: Film) = filmsDao.delete(film)

    @WorkerThread
    fun getFilmById(filmId: Int): Flow<Film> = filmsDao.getFilm(filmId)


}