package com.gohool.booksfilmslist.adapters

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.gohool.booksfilmslist.classes.Book

class BookDataBaseHelper(context : Context): SQLiteOpenHelper(context, TableInfo.TABLE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TEABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE)
        db?.execSQL(BasicCommand.SQL_CREATE_TEABLE)
    }

    fun getBooks() : ArrayList<Book> {
        val db : SQLiteDatabase = this.readableDatabase
        val cursor = db.rawQuery(BasicCommand.SQL_GET_ALL, null)
        val books = ArrayList<Book>()
        if(cursor.count==0)
            Log.d("MyLog","No records in Book Database found")
        else{
            cursor.moveToFirst()
            while (!cursor.isAfterLast()){
                val book = Book(cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                                cursor.getString(cursor.getColumnIndex(TableInfo.TABLE_COLUMN_TITTLE)),
                                cursor.getString(cursor.getColumnIndex(TableInfo.TABLE_COLUMN_AUTHOR)),
                                cursor.getString(cursor.getColumnIndex(TableInfo.TABLE_COLUMN_TYPE)),
                                cursor.getInt(cursor.getColumnIndex(TableInfo.TABLE_COLUMN_PRIORITY)),
                                cursor.getString(cursor.getColumnIndex(TableInfo.TABLE_COLUMN_DESCRIPTION)))

                books.add(book)

                cursor.moveToNext()
            }
        }

        cursor.close()
        db.close()

        return books
    }

    fun deleteBook(id :Int) : Boolean{
        var result = false

        val db = this.writableDatabase

        try{
            //db.delete(TableInfo.TABLE_NAME,TableInfo.TABLE_COLUMN_TITTLE+"=?", arrayOf(tittle))
            db.delete(TableInfo.TABLE_NAME,BaseColumns._ID+"=?", arrayOf(id.toString()))

            Log.d("MyLog", "Book sucessfuly deleted")
            result = true
        }catch (e : Exception){
            Log.d("MyLog", "Error deleting book")
        }
        db.close()
        return result
    }

    fun updateBook(value : ContentValues, id : Int){
        val db = this.writableDatabase
        db.update(TableInfo.TABLE_NAME, value, BaseColumns._ID+"=?", arrayOf(id.toString()))
        db.close()
    }

    fun insertBook(value : ContentValues){
        val db = this.writableDatabase
        db.insertOrThrow(TableInfo.TABLE_NAME, null, value)
        db.close()
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

    const val SQL_GET_ALL = "SELECT * FROM ${TableInfo.TABLE_NAME}"

}