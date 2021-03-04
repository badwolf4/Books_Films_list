package com.gohool.booksfilmslist.data.films

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Film::class), version = 1, exportSchema = false)
abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmsDao() : FilmsDao

    companion object{
        @Volatile
        private var INSTANCE: FilmsDatabase? = null

        fun getInstance(context: Context) : FilmsDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    FilmsDatabase::class.java,
                    "films_table"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }


    }
}