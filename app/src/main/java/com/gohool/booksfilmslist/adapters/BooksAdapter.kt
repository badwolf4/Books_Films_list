package com.gohool.booksfilmslist.adapters

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.viewHolders.BookViewHolder
import com.gohool.booksfilmslist.classes.Book

class BooksAdapter(private val books : List<Book>, var onClickListener: onBookItemClickListener) :RecyclerView.Adapter<BookViewHolder>() {

    //class BookViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        return BookViewHolder(
            inflater, parent
        )
//       return BookViewHolder(
//           LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
//       )
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        //val book : Book = books[position]
        holder.bind(books.get(position),onClickListener)

        /*
        holder.view.bookTittle.text = book.tittle
        holder.view.bookAuthor.text = book.author
        holder.view.bookType.text = book.type
        holder.view.bookPriority.text = book.priority.toString()
         */

    }
}

interface onBookItemClickListener {
    fun onItemClick(item: Book, position: Int)
}