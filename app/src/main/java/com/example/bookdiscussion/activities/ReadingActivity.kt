package com.example.bookdiscussion.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdiscussion.adapters.BookArrayAdapter
import com.example.bookdiscussion.databinding.ActivityLikedBooksBinding
import com.example.bookdiscussion.databinding.ActivityReadingBinding
import com.example.bookdiscussion.view.model.BookViewModel

class ReadingActivity : AppCompatActivity(){
    private lateinit var binding: ActivityReadingBinding
    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewBooks = binding.recyclerView
        val bookAdapter = BookArrayAdapter(layoutInflater)

        recyclerViewBooks.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            adapter = bookAdapter
        }

        viewModel.readingBooks.observe(
            this,
            Observer { booksList ->
                bookAdapter.submitList(booksList.toList())
            }
        )

    }
}