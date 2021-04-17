package com.example.bookdiscussion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.bookdiscussion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookAdapter = BookAdapter(layoutInflater)

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
    }
}