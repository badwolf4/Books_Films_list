package com.gohool.booksfilmslist.viewHolders

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.data.books.Book
import com.gohool.booksfilmslist.ui.fragments.BooksFragmetDirections
import com.gohool.booksfilmslist.viewModels.BookViewModel
import kotlinx.android.synthetic.main.film_item.view.*

class BookViewHolder(inflater:LayoutInflater, parent: ViewGroup) :RecyclerView.ViewHolder(inflater.inflate(
    R.layout.book_item,parent, false)) {

    private var mTittleView: TextView? = null
    private var mAuthorView: TextView? = null
    //private var mTypeView: TextView? = null
    private var mPriorityView: TextView? = null
    private var bookCardView: CardView? = null

            init {
                mTittleView = itemView.findViewById<TextView>(R.id.bok_tittle)
                mAuthorView = itemView.findViewById<TextView>(R.id.bok_author)
                //mTypeView = itemView.findViewById<TextView>(R.id.bok_type)
                mPriorityView = itemView.findViewById<TextView>(R.id.book_priority)
                bookCardView = itemView.findViewById<CardView>(R.id.book_cardView)

            }

    fun bind(book : Book, viewModel: BookViewModel){
        Log.d("MyLog", "Book tittle: ${book.title}")

                mTittleView?.text = book.title
                mAuthorView?.text = book.author
                mPriorityView?.text = book.priority.toString()


                itemView.findViewById<CardView>(R.id.book_cardView).setOnClickListener{
                    Log.d("PassingBoookId: ","id is ${book.id}")
                    Log.d("PassingBoookId: ","Trying to pass id")
                    Log.d("MyLog ","Some stupid log")
                    val action = BooksFragmetDirections.actionBooksFragmetToBookDetailedFragment(bookid = book.id)
                    itemView.findNavController().navigate(action)

                }

                itemView.delete_item.setOnClickListener{
                    AlertDialog.Builder(itemView.context)
                        .setMessage("Are you sure that you want to delete?")
                        .setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which ->
                            viewModel.deleteBook(book)
                            Toast.makeText(itemView.context,"Book deleted", Toast.LENGTH_SHORT).show()
                        })
                        .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        })
                        .show()
                }





        }





}