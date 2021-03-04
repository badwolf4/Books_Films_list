package com.gohool.booksfilmslist.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.viewHolders.BookViewHolder
import com.gohool.booksfilmslist.data.books.Book
import com.gohool.booksfilmslist.ui.fragments.BooksFragmet
import com.gohool.booksfilmslist.viewModels.BookViewModel
import kotlinx.android.synthetic.main.book_item.view.*

class BooksAdapter(val books : List<Book>, val viewModel: BookViewModel) :RecyclerView.Adapter<BookViewHolder>() {

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
        holder.bind( books.get(position), viewModel)

        val tittle  = books.get(position).title
        val bookId  = books.get(position).id
        //val bookId = position.plus(1)

    }


}

