package com.example.bookdiscussion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookdiscussion.databinding.ActivityBookBinding
import com.example.bookdiscussion.databinding.ActivityBookCommentsBinding

class BookComments : AppCompatActivity() {

    private lateinit var binding : ActivityBookCommentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}