package com.gohool.booksfilmslist.adapters

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class BookDataBaseHelper(context : Context): SQLiteOpenHelper(context, TableInfo.TABLE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TEABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE)
        db?.execSQL(BasicCommand.SQL_CREATE_TEABLE)
    }
}

object TableInfo : BaseColumns{
    const val TABLE_NAME = "books"
    const val TABLE_COLUMN_TITTLE = "tittle"
    const val TABLE_COLUMN_AUTHOR = "author"
    const val TABLE_COLUMN_TYPE = "type"
    const val TABLE_COLUMN_PRIORITY = "priority"
    const val TABLE_COLUMN_DESCRIPTION = "description"
}

object BasicCommand {
    const val SQL_CREATE_TEABLE : String =
        "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY,"+
                "${TableInfo.TABLE_COLUMN_TITTLE} TEXT, " +
                "${TableInfo.TABLE_COLUMN_AUTHOR} TEXT NOT NULL, " +
                "${TableInfo.TABLE_COLUMN_TYPE} TEXT NOT NULL, " +
                "${TableInfo.TABLE_COLUMN_PRIORITY} INTEGER, " +
                "${TableInfo.TABLE_COLUMN_DESCRIPTION} TEXT NOT NULL)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"

}