package com.example.bookdiscussion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdiscussion.adapters.BookArrayAdapter
import com.example.bookdiscussion.adapters.BookListAdapter
import com.example.bookdiscussion.adapters.CommentAdapter
import com.example.bookdiscussion.databinding.ActivityBookBinding
import com.example.bookdiscussion.databinding.ActivityBookCommentsBinding
import com.example.bookdiscussion.models.Book
import com.example.bookdiscussion.models.Comment
import com.example.bookdiscussion.view.model.BookViewModel

class BookComments : AppCompatActivity() {

    private lateinit var binding : ActivityBookCommentsBinding
    private val viewModel : BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerViewBooks = binding.recyclerView
        val bookId = intent.getStringExtra("bookId")
        var quotes : MutableList<Comment> = mutableListOf<Comment>()

        viewModel.getComments(bookId.toString())

        fun updateRecycler() {
            recyclerViewBooks.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
                adapter =
                    CommentAdapter(
                        quotes,
                        layoutInflater
                    )
            }
        }

        viewModel.quotes.observe(
            this,
            Observer { qts ->
                quotes = qts as MutableList<Comment>
                Log.d("livedata", quotes.toString())
                updateRecycler()
            }
        )

        binding.addCommentButton.setOnClickListener {
            var quote = Comment(bookId.toString(), binding.commentText.text.toString())
            quotes = viewModel.commentBook(quote, quotes)
        }
    }
}