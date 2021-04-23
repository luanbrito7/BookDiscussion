package com.example.bookdiscussion

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
<<<<<<< HEAD
import com.example.bookdiscussion.booksApi.BookApi
=======
import androidx.recyclerview.widget.GridLayoutManager
>>>>>>> 55f4645 (WIP)
import com.example.bookdiscussion.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel.insert(Mocks.books[0]) //this line is for testing

        val bookAdapter = BookArrayAdapter(layoutInflater)

        val recyclerViewBooks = binding.recyclerView

        recyclerViewBooks.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            adapter = bookAdapter
        }

        viewModel.books.observe(
            this,
            Observer { booksList ->
                bookAdapter.submitList(booksList.toList())
            }
        )

        binding.addButton.setOnClickListener {
            val addBookIntent = Intent(this@MainActivity, AddBookActivity::class.java)
            this@MainActivity.startActivity(addBookIntent)
        }

    }
}