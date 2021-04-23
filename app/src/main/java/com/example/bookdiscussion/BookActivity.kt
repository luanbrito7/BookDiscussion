package com.example.bookdiscussion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.bookdiscussion.databinding.ActivityBookBinding
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class  BookActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBookBinding

    private val viewModel : BookViewModel by viewModels()

    private var daoJob : Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val description = intent.getStringExtra("description")

       // binding.bookId.text = id
        binding.bookTitle.text = title
        binding.bookAuthor.text = author
        binding.bookDescription.text = description

        val likeButton: Button = findViewById(R.id.likeButton)
        likeButton.setOnClickListener { likeBook("xalala") }

        val shelfButton: Button = findViewById(R.id.addShelfButton)
        shelfButton.setOnClickListener {
            if (id != null) {
                addBookToShelf(id)
            }
        }
    }

    fun likeBook(id: String?) {
        Toast.makeText(applicationContext, "Book added to your liked books!", LENGTH_SHORT).show()
    }

    fun addBookToShelf(id: String) {
        Log.d("Id! ", id)
        var book: Any = viewModel.getBookById(id)
        Log.d("AQUI! ", book.toString())
        Toast.makeText(applicationContext, "Book added to your shelf!", LENGTH_SHORT).show()
    }
}
