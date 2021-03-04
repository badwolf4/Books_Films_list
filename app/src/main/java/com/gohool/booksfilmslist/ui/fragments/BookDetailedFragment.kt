package com.gohool.booksfilmslist.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController

import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.data.books.Book
import com.gohool.booksfilmslist.data.books.BookDatabase
import com.gohool.booksfilmslist.data.books.BookRepository
import com.gohool.booksfilmslist.databinding.FragmentBookItemDetailedBinding
import com.gohool.booksfilmslist.viewModels.BookViewModel
import com.gohool.booksfilmslist.viewModels.BooksViewModelFactory


class BookDetailedFragment : Fragment() {

    var book: LiveData<Book>? = null
    var bookId: Int?=null

    val database by lazy { BookDatabase.getInstance(this.requireActivity().applicationContext) }
    val repository by lazy { BookRepository(database.booksDao()) }

    private val viewModel: BookViewModel by activityViewModels {
        BooksViewModelFactory(repository)
    }

    lateinit var binding: FragmentBookItemDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_item_detailed, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            bookId = it.getInt("bookid")
            bookId?.let {
                book = viewModel.getBookById(it)
                book?.observe(viewLifecycleOwner, {
                    binding.apply {
                        tittleDetailed.text = it.title
                        authorDetailed.text = it.author
                        typeDetailed.text = it.type
                        priorityDetailed.text = it.priority.toString()
                        descriptionDetailed.text = it.description
                    }
                })
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detailed_option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle = Bundle()
        val id = item.itemId
        when(id){
            R.id.edit_button-> {
                 bookId?.let {
                     val action = BookDetailedFragmentDirections.actionBookDetailedFragmentToBookEditFragment2(it)
                     findNavController().navigate(action)
                }

            }
            R.id.delete_button->
            {
                var alertDialog  = AlertDialog.Builder(context)
                    .setMessage("Are you sure that you want to delete ${book?.value?.title}?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog,which ->
                        book?.value?.let { viewModel.deleteBook(it) }
                        findNavController().navigateUp()



            }).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> })
                    .show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
