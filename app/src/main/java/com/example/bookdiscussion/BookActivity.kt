package com.example.bookdiscussion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookdiscussion.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")

        binding.bookTitle.text = title
        binding.bookAuthor.text = author
    }
}