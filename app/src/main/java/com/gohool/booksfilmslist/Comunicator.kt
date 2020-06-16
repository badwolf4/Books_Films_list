package com.gohool.booksfilmslist

import com.gohool.booksfilmslist.classes.Book
import com.gohool.booksfilmslist.classes.Film

public interface Comunicator {
    fun nextFragment(id :Int)
    fun nextDetailedBookItemFragment(book : Book)
    fun nextDetailedFilmItemFragment(film : Film)
//coment
}