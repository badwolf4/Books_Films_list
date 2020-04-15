package com.gohool.booksfilmslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.classes.Film
import com.gohool.booksfilmslist.viewHolders.FilmViewHolder

class FilmsAdapter(private  val films : List<Film>, var onClickListener: onFilmItemClickListener) : RecyclerView.Adapter<FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FilmViewHolder(
            inflater,
            parent
        )
    }

    override fun getItemCount() = films.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        //val film : Film = films[position]
        holder.bind(films.get(position),onClickListener)
    }

}

interface onFilmItemClickListener{
    fun onItemClick(item: Film, position: Int)
}