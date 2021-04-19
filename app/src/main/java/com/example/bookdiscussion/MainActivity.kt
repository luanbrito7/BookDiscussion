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

        val recyclerViewBooks = binding.recyclerView

        binding.searchButton.setOnClickListener {
            jobNetwork = coroutineScope.launch {
                var books = viewModel.request(binding.searchText.text.toString())
                recyclerViewBooks.apply {
                    layoutManager = LinearLayoutManager(applicationContext)
                    addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
                    adapter = BookAdapter(books, layoutInflater)
                }
            }
        }

    }
}