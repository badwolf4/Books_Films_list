package com.gohool.booksfilmslist

import com.gohool.booksfilmslist.classes.Book

public interface Comunicator {
    fun nextFragment(id :Int)
    fun nextDetailedBookItemFragment(book : Book)

}