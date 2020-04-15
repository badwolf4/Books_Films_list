package com.gohool.booksfilmslist.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.onBookItemClickListener
import com.gohool.booksfilmslist.classes.Book

class BookViewHolder(inflater:LayoutInflater, parent: ViewGroup) :RecyclerView.ViewHolder(inflater.inflate(
    R.layout.book_item,parent, false)) {

    private var mTittleView: TextView? = null
    private var mAuthorView: TextView? = null
    //private var mTypeView: TextView? = null
    private var mPriorityView: TextView? = null

            init {
                mTittleView = itemView.findViewById<TextView>(R.id.bok_tittle)
                mAuthorView = itemView.findViewById<TextView>(R.id.bok_author)
                //mTypeView = itemView.findViewById<TextView>(R.id.bok_type)
                mPriorityView = itemView.findViewById<TextView>(R.id.book_priority)
            }

    fun bind(book : Book, action: onBookItemClickListener){
        mTittleView?.text = book.tittle
        mAuthorView?.text = book.author
        //mTypeView?.text = book.type
        mPriorityView?.text = book.priority.toString()
        itemView.setOnClickListener{
            action.onItemClick(book, adapterPosition)
        }
    }

//    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit) : T {
//        itemView.setOnClickListener{
//            event.invoke(getAdapterPosition(),getItemViewType())
//        }
//        return this
//    }

}