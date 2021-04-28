package com.example.bookdiscussion.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdiscussion.adapters.BookArrayAdapter
import com.example.bookdiscussion.databinding.ActivityLikedBooksBinding
import com.example.bookdiscussion.databinding.ActivityToReadBinding
import com.example.bookdiscussion.view.model.BookViewModel

class ToReadActivity : AppCompatActivity(){
    private lateinit var binding: ActivityToReadBinding
    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewBooks = binding.recyclerView
        val bookAdapter = BookArrayAdapter(layoutInflater)

        recyclerViewBooks.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            adapter = bookAdapter
        }

        viewModel.toReadBooks.observe(
            this,
            Observer { booksList ->
                bookAdapter.submitList(booksList.toList())
            }
        )

    }
}