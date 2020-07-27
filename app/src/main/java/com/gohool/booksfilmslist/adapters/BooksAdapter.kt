package com.gohool.booksfilmslist.adapters

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.viewHolders.BookViewHolder
import com.gohool.booksfilmslist.classes.Book
import com.gohool.booksfilmslist.fragments.BooksFragmet
import kotlinx.android.synthetic.main.book_item.view.*
import kotlinx.android.synthetic.main.fragment_film_detailed.view.*

class BooksAdapter(var onClickListener: onBookItemClickListener, val books : ArrayList<Book>) :RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookViewHolder(
            inflater, parent
        )

    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(onClickListener, books.get(position))

        val tittle  = books.get(position).tittle
        val bookId = position.plus(1)
        holder.itemView.delete_item.setOnClickListener {

            var alertDialog  = AlertDialog.Builder(holder.itemView.context).setTitle("Warning")
                .setMessage("Are you sure that you want to delete ${tittle}?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog,which ->
                    Log.d("MyLog", "Inside delete button")
                    Log.d("MyLog", "Books list size: ${books.size}")
                    val selection = "${TableInfo.TABLE_COLUMN_TITTLE} LIKE ?"

                    BooksFragmet.dbHelper.deleteBook( tittle)
                    Log.d("MyLog", "Book deleted")

                    books.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,books.size)
                    Log.d("MyLog", "Books list size: ${books.size}")
                    Toast.makeText(holder.itemView.context,"Book deleted", Toast.LENGTH_SHORT).show()

                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> })
                .setIcon(R.drawable.ic_baseline_warning_24)
                .show()

        }


    }


}

interface onBookItemClickListener {
    fun onItemClick(book: Book, position: Int)
}

