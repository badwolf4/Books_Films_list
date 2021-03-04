package com.gohool.booksfilmslist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gohool.booksfilmslist.BooksFilmsApplication
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.data.Constants
import com.gohool.booksfilmslist.databinding.ActivityMainBinding
import com.gohool.booksfilmslist.ui.fragments.*
import com.gohool.booksfilmslist.viewModels.FilmsViewModel
import com.gohool.booksfilmslist.viewModels.FilmsViewModelFactory
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){

    val manager = supportFragmentManager


    private val viewModel: FilmsViewModel by viewModels {
        FilmsViewModelFactory((application as BooksFilmsApplication).repository)
    }


    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //showStartFragment()

        binding.navigationView.setNavigationItemSelectedListener {menuItem->

            when(menuItem.itemId){
                R.id.books_option -> {
//                    nextFragment(Constants.BOOKS_FRAGMENT)
                }
                R.id.films_option -> {
//                    nextFragment(Constants.FILMS_FRAGMENT)
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    fun showStartFragment()
//    {
//        val transaction = manager.beginTransaction()
//        val fragment = StartFragment()
//        transaction.replace(R.id.main_fragment, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }


}
