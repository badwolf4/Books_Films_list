package com.gohool.booksfilmslist.fragments

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.BookDataBaseHelper
import com.gohool.booksfilmslist.adapters.TableInfo
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_add_book.view.*

class BookEditFragment : Fragment() {

    lateinit var seekBar : SeekBar
    lateinit var priority : TextView
    lateinit var comunicator : Comunicator
    var id : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        id =arguments?.getInt("bookId")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_add_book, container, false)
        comunicator = activity as Comunicator

        priority = view.findViewById(R.id.priority_value) as TextView
        seekBar = view.findViewById(R.id.priority) as SeekBar
        seekBar.max = 10

        val bookTittle = view.findViewById<EditText>(R.id.bookTittle)
        Log.d("MyLog", "Trying to read state")
        arguments?.getString("tittle").let {
            view.bookTittle.setText(it)
        }

        arguments?.getString("author").let {
            view.bookAuthor.setText(it)
        }

        arguments?.getString("type").let {
            view.bookType.setText(it)
        }

        arguments?.getString("description").let {
            view.bookDescription.setText(it)
        }
        arguments?.getInt("priority").let {
            if (it != null) {
                seekBar.setProgress(it,false)
            }
            priority.text = it.toString()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                priority.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    priority.text = seekBar?.progress.toString()

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                priority.text = seekBar?.progress.toString()
            }

        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_item_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            val tittle = view?.bookTittle?.text.toString()
            val author = view?.bookAuthor?.text.toString()
            val type = view?.bookType?.text.toString()
            val priorityValue = seekBar.progress
            val description = view?.bookDescription?.text.toString()

            if(!tittle.isNullOrEmpty() || !author.isNullOrEmpty() || !type.isNullOrEmpty() || !description.isNullOrEmpty())
            {
                val value = ContentValues()

                value.put(TableInfo.TABLE_COLUMN_TITTLE, tittle)
                value.put(TableInfo.TABLE_COLUMN_AUTHOR, author)
                value.put(TableInfo.TABLE_COLUMN_TYPE, type)
                value.put(TableInfo.TABLE_COLUMN_PRIORITY, priorityValue)
                value.put(TableInfo.TABLE_COLUMN_DESCRIPTION, description)

                if(id!=null) {
                    BooksFragmet.dbHelper.updateBook(value, id!!)
                }
                else {
                    BooksFragmet.dbHelper.insertBook(value)
                }

            }
            else{
                Toast.makeText(context, "Book with empty fields can not be added", Toast.LENGTH_LONG).show()
            }
            comunicator.nextFragment(R.id.booksButton)

        return super.onOptionsItemSelected(item)
    }

}
