package com.example.bookdiscussion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.bookdiscussion.adapters.BookArrayAdapter
import com.example.bookdiscussion.view.model.BookViewModel
import com.example.bookdiscussion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookAdapter =
            BookArrayAdapter(layoutInflater)

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

        binding.likedBooksButton.setOnClickListener {
            val addBookIntent = Intent(this, LikedBooksActivity::class.java)
            startActivity(addBookIntent)
        }

        binding.readButton.setOnClickListener {
            val addBookIntent = Intent(this@MainActivity, ReadActivity::class.java)
            this@MainActivity.startActivity(addBookIntent)
        }

        binding.readingButton.setOnClickListener {
            val addBookIntent = Intent(this@MainActivity, ReadingActivity::class.java)
            this@MainActivity.startActivity(addBookIntent)
        }

        binding.toReadButton.setOnClickListener {
            val addBookIntent = Intent(this@MainActivity, ToReadActivity::class.java)
            this@MainActivity.startActivity(addBookIntent)
        }


    }
}