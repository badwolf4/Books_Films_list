package com.gohool.booksfilmslist

import android.os.Bundle
import com.gohool.booksfilmslist.classes.Book
import com.gohool.booksfilmslist.classes.Constants
import com.gohool.booksfilmslist.classes.Film

public interface Comunicator {
    fun nextFragment(c: Constants)
    fun nextFragment(c: Constants, bundle: Bundle)

}

