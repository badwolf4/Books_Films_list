package com.gohool.booksfilmslist.filmsRoomDatabase

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmsDao() : FilmsDao
    companion object{
        private var instance : FilmsDatabase? = null

        fun getInstance(context: Context) : FilmsDatabase?{
            Log.d("Film Database:", "Getting instance of database")
            if(instance==null){
                Log.d("Film Database:", "Building database")
                instance = Room.databaseBuilder(context,
                    FilmsDatabase::class.java,
                    "films_table").fallbackToDestructiveMigration().build()
                Log.d("Film Database:", "Database built")
            }
            return instance
        }

        fun deleteInstanceOfDatabase(){
            instance=null
        }

    }
}