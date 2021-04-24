package com.example.bookdiscussion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import com.example.bookdiscussion.databinding.ActivityBookBinding
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bookdiscussion.models.Book
import com.example.bookdiscussion.view.model.BookViewModel
import com.example.bookdiscussion.R
import kotlinx.coroutines.Job


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
        val rate = intent.getFloatExtra("rate", 0F)

       // binding.bookId.text = id
        binding.bookTitle.text = title
        binding.bookAuthor.text = author
        binding.bookDescription.text = description
        binding.ratingBar.rating = rate

        val likeButton: Button = binding.likeButton
        likeButton.setOnClickListener {
            if (id != null) {
                likeBook(id)
            }
        }

        val shelfButton: Button = binding.addShelfButton
        shelfButton.setOnClickListener {
            if (id != null) {
                addBookToShelf(id, title, author, description)
            }
        }

        val toReadButton: Button = binding.toReadButton
        toReadButton.setOnClickListener {
            if (id != null) {
                markBookAs(id, "TO_READ")
            }
        }

        val readButton: Button = binding.readButton
        readButton.setOnClickListener {
            if (id != null) {
                markBookAs(id, "READ")
            }
        }

        val readingButton: Button = binding.readingButton
        readingButton.setOnClickListener {
            if (id != null) {
                markBookAs(id, "READING")
            }
        }


        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

            var liveData: MutableLiveData<Book>?

            if (id != null) {
                liveData = viewModel.getBookById(id)
                liveData.observe(
                    this,
                    Observer { b ->
                        if (b != null) {
                            b.rate = rating.toInt()
                            viewModel.update(b)
                            Toast.makeText(applicationContext, "You rated this book as ${rating.toInt()} stars", LENGTH_LONG).show()
                        } else {
                            Toast.makeText(applicationContext, "You must add to your shelf before rating", LENGTH_LONG).show()
                        }

                    }
                )
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
            book = Book(
                id,
                title,
                description,
                "",
                author,
                false,
                false,
                false,
                0,
                false
            )
            viewModel.insert(book)
        }

        Toast.makeText(applicationContext, "Book added to your shelf!", LENGTH_SHORT).show()

        val addBookIntent = Intent(this, MainActivity::class.java)
        addBookIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(addBookIntent)
    }

    fun markBookAs(id: String, markAs: String) {
        var liveData: MutableLiveData<Book>?

        if (id != null) {
            liveData = viewModel.getBookById(id)
            liveData.observe(
                this,
                Observer { b ->
                    if (markAs == "READING") {
                        b.reading = true
                        b.read = false
                        b.wantToRead = false
                        viewModel.update(b)
                        Toast.makeText(applicationContext, "Book marked as 'reading'!", LENGTH_SHORT).show()
                    } else if (markAs == "READ") {
                        b.reading = false
                        b.read = true
                        b.wantToRead = false
                        viewModel.update(b)
                        Toast.makeText(applicationContext, "Book marked as 'read'!", LENGTH_SHORT).show()
                    } else if (markAs == "TO_READ") {
                        b.reading = false
                        b.read = false
                        b.wantToRead = true
                        viewModel.update(b)
                        Toast.makeText(applicationContext, "Book marked as 'to read'!", LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "Something went wrong!", LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}
