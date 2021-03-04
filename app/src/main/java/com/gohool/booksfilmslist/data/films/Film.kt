package com.gohool.booksfilmslist.data.films

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="films_table")
data class Film (

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
    @ColumnInfo(name = "filmId")
    var filmId : Int = 0
}

