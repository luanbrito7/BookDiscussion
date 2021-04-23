package com.example.bookdiscussion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.bookdiscussion.databinding.ActivityBookBinding
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
        likeButton.setOnClickListener {
            if (id != null) {
                likeBook(id)
            }
        }

        val shelfButton: Button = findViewById(R.id.addShelfButton)
        shelfButton.setOnClickListener {
            if (id != null) {
                addBookToShelf(id, title, author, description)
            }
        }
    }

    fun likeBook(id: String?) {
        var liveData: MutableLiveData<Book>?

        if (id != null) {
            liveData = viewModel.getBookById(id)
            liveData.observe(
                this,
                Observer { b ->
                    b.liked = true
                    viewModel.update(b)
                }
            )
        }

        Toast.makeText(applicationContext, "Book added to your liked books!", LENGTH_SHORT).show()
    }

    /*
    * TODO
    * Ajustar a recuperacão da imagem
    * */
    fun addBookToShelf(id: String, title: String?, author: String?, description: String?) {
        var book: Book?
        if (title != null && author != null && description != null) {
            book = Book(id, title, description, "", author, false, false, false, 0, false)
            viewModel.insert(book)
        }

        Toast.makeText(applicationContext, "Book added to your shelf!", LENGTH_SHORT).show()
    }
}
