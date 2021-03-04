package com.gohool.booksfilmslist.data.books

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book (

    @ColumnInfo(name = "title")
    var title : String = "",

    @ColumnInfo(name = "author")
    var author : String = "",

    @ColumnInfo(name = "genre")
    var type : String = "",

    @ColumnInfo(name = "priority")
    var priority : Int = 0,

    @ColumnInfo(name = "description")
    var description : String = ""
){
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}