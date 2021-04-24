package com.example.bookdiscussion.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdiscussion.adapters.BookArrayAdapter
import com.example.bookdiscussion.databinding.ActivityLikedBooksBinding
import com.example.bookdiscussion.view.model.BookViewModel


class LikedBooksActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLikedBooksBinding
    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikedBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewBooks = binding.recyclerView
        val bookAdapter = BookArrayAdapter(layoutInflater)

        recyclerViewBooks.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            adapter = bookAdapter
        }

        viewModel.likedBooks.observe(
            this,
            Observer { booksList ->
                bookAdapter.submitList(booksList.toList())
            }
        )

    }
}
