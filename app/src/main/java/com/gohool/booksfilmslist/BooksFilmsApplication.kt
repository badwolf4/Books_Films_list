package com.gohool.booksfilmslist

import android.app.Application
import com.gohool.booksfilmslist.data.films.FilmsDatabase
import com.gohool.booksfilmslist.data.films.FilmsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BooksFilmsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { FilmsDatabase.getInstance(this) }
    val repository by lazy { FilmsRepository(database.filmsDao()) }

}