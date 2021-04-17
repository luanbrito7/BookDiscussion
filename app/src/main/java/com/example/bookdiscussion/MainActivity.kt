package com.example.bookdiscussion

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.bookdiscussion.booksApi.BookApi
import com.example.bookdiscussion.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var jobNetwork : Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel.insert(Mocks.books[0]) //this line is for testing

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

        binding.searchButton.setOnClickListener {
            jobNetwork = coroutineScope.launch {
                viewModel.request(binding.searchText.text.toString()).forEach {
                    Log.d("API", it.key + " -> " + it.value.toString())
                }
            }
        }

    }
}