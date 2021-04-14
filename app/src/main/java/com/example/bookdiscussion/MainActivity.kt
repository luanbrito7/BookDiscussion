package com.example.bookdiscussion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdiscussion.databinding.ActivityBookBinding
import com.example.bookdiscussion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewBooks = binding.recyclerView

        recyclerViewBooks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            adapter = BookAdapter(Mocks.books, layoutInflater)
        }
    }
}