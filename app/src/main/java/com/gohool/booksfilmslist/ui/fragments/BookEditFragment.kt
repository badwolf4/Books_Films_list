package com.gohool.booksfilmslist.ui.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.data.Constants
import com.gohool.booksfilmslist.data.books.Book
import com.gohool.booksfilmslist.data.books.BookDatabase
import com.gohool.booksfilmslist.data.books.BookRepository
import com.gohool.booksfilmslist.databinding.FragmentAddBookBinding
import com.gohool.booksfilmslist.viewModels.BookViewModel
import com.gohool.booksfilmslist.viewModels.BooksViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_book.view.*

class BookEditFragment : Fragment() {

    var id : Int? = null
    var book: LiveData<Book>? = null

    val database by lazy { BookDatabase.getInstance(this.requireActivity().applicationContext) }
    val repository by lazy { BookRepository(database.booksDao()) }

    private val viewModel: BookViewModel by activityViewModels {
        BooksViewModelFactory(repository)
    }

    lateinit var binding: FragmentAddBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        id =arguments?.getInt("bookId")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view : View = inflater.inflate(R.layout.fragment_add_book, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_book,container,false)
        val view = binding.root

        binding.apply {
            priority.max = 10

            priority.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    priorityValue.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    priorityValue.text = seekBar?.progress.toString()

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    priorityValue.text = seekBar?.progress.toString()
                }

            })
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = it.getInt("bookid")
            if(id!=-1){
                id?.let {
                    book = viewModel.getBookById(it)
                    book?.observe(viewLifecycleOwner, {
                        binding.apply {
                            bookAuthor.setText(it.author)
                            bookDescription.setText(it.description)
                            bookTittle.setText(it.title)
                            priority.setProgress(it.priority)
                            priorityValue.setText(it.priority.toString())
                            bookType.setText(it.type)

                        }
                    })
                }
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_item_opion_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val newBook = createBook()
        newBook?.let {
            book?.value?.let {
                newBook.id = id!!
                viewModel.updateBook(newBook)
                findNavController().navigateUp()
            } ?: kotlin.run{
                viewModel.insertBook(newBook)
                findNavController().navigateUp()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun createBook() : Book?{
        binding.apply{
            if(bookTittle.text.toString().isEmpty()
                ||bookAuthor.text.toString().isEmpty()
                ||bookDescription.text.toString().isEmpty()
                ||bookType.text.toString().isEmpty()
                ||priorityValue.text.toString().isEmpty())
                    return null

            return  Book(bookTittle.text.toString(),
                   bookAuthor.text.toString(),
                    bookType.text.toString(),
                    priorityValue.text.toString().toInt(),
                    bookDescription.text.toString())
        }
    }

}
