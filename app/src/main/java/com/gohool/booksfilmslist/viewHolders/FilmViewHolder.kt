package com.gohool.booksfilmslist.viewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.onFilmItemClickListener
import com.gohool.booksfilmslist.filmsRoomDatabase.Film

class FilmViewHolder(inflater: LayoutInflater,parent: ViewGroup ) :RecyclerView.ViewHolder(inflater.inflate(
    R.layout.film_item, parent, false)) {

    private var mTittleTextView: TextView? = null
    private var mYearTextView: TextView? = null
    private var mPriorityTextView: TextView? = null

    init {
        mTittleTextView = itemView.findViewById(R.id.film_tittle_textView)
        mYearTextView = itemView.findViewById(R.id.film_year_TextView)
        mPriorityTextView = itemView.findViewById(R.id.film_priority_textView)
    }

    fun bind(film: Film, action: onFilmItemClickListener){
        mTittleTextView?.text = film.tittle
        mYearTextView?.text = film.year.toString()
        mPriorityTextView?.text = film.priority.toString()
        itemView.setOnClickListener{
            action.onItemClick(film,adapterPosition)
        }
    }
}