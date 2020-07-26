package com.gohool.booksfilmslist.adapters

import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.viewHolders.BookViewHolder
import com.gohool.booksfilmslist.classes.Book

class BooksAdapter(var onClickListener: onBookItemClickListener, val db : SQLiteDatabase) :RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        return BookViewHolder(
            inflater, parent
        )

    }

    override fun getItemCount(): Int {
        val cursor = db.query(TableInfo.TABLE_NAME, null, null,
            null, null, null, null)
        val count = cursor.count
        cursor.close()
        return count
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(position,onClickListener,db)
        //notifyDataSetChanged()
        //notifyItemRemoved(position)
        //notifyItemRangeChanged(1,itemCount)
    }
}

interface onBookItemClickListener {
    fun onItemClick(book: Book, position: Int)
}

