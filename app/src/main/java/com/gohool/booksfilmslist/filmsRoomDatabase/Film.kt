package com.gohool.booksfilmslist.filmsRoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="films_table")
data class Film (
//    @PrimaryKey
//    var filmId : Int,

    @ColumnInfo(name = "tittle")
    val tittle : String,

    @ColumnInfo(name = "type")
    val type : String,

    @ColumnInfo(name = "year")
    val year : Int,

    @ColumnInfo(name = "priority")
    val priority : Int,

    @ColumnInfo(name = "description")
    val description : String
){
    @PrimaryKey(autoGenerate = true)
    var filmId : Int = 0
}

