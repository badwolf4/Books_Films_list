package com.gohool.booksfilmslist

import android.os.Bundle
import com.gohool.booksfilmslist.classes.Book
import com.gohool.booksfilmslist.classes.Film

public interface Comunicator {
    fun nextFragment(id :Int)
    fun nextDetailedBookItemFragment(bundle : Bundle)
    fun nextDetailedFilmItemFragment(film : Film)
    fun nextEditFragment(bundle : Bundle)
    fun nextDeletedBook(position : Int)


}