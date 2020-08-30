package com.gohool.booksfilmslist.viewHolders

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.TableInfo
import com.gohool.booksfilmslist.adapters.onBookItemClickListener
import com.gohool.booksfilmslist.classes.Book

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

    fun bind(action: onBookItemClickListener, book : Book){
        Log.d("MyLog", "Book tittle: ${book.tittle}")

                mTittleView?.text = book.tittle
                mAuthorView?.text = book.author
                mPriorityView?.text = book.priority.toString()


                itemView.setOnClickListener{
                    action.onItemClick(book,adapterPosition.plus(1))
                }



        }

//      bookCardView?.setOnClickListener {
//          val bundle = Bundle()
//
//          bundle.putString("tittle", book.tittle)
//          bundle.putString("author",book.author)
//          bundle.putString("description",book.description)
//          bundle.putString("type",book.type)
//          bundle.putInt("priority",book.priority)
//
//
//          comunicator.nextDetailedBookItemFragment(bundle)
//            Toast.makeText(itemView.context, mTittleView?.text, Toast.LENGTH_SHORT).show()
//        }






}