package com.example.bookdiscussion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdiscussion.view.model.BookViewModel
import com.example.bookdiscussion.adapters.BookListAdapter
import com.example.bookdiscussion.databinding.ActivityAddBookBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddBookActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddBookBinding
    private var jobNetwork : Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewBooks = binding.recyclerView

        binding.searchButton.setOnClickListener {
            jobNetwork = coroutineScope.launch {
                var books = viewModel.request(binding.searchText.text.toString())
                recyclerViewBooks.apply {
                    layoutManager = LinearLayoutManager(applicationContext)
                    addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
                    adapter =
                        BookListAdapter(
                            books,
                            layoutInflater
                        )
                }
            }
        }
    }
}
