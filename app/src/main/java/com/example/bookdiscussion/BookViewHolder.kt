package com.example.bookdiscussion

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdiscussion.databinding.BookrowBinding
import com.squareup.picasso.Picasso

class BookViewHolder(private val binding: BookrowBinding) :
    RecyclerView.ViewHolder(binding.root)
{
    var title : String = ""
    var image_url : String = ""
    var author : String = ""

    init {
        binding.root.setOnClickListener {
            val c = binding.title.context
            val bookIntent = Intent(c, BookActivity::class.java)
            bookIntent.putExtra("title", title)
            bookIntent.putExtra("image_url", image_url)
            bookIntent.putExtra("author", author)
            c.startActivity(bookIntent)
        }
    }

    fun bindTo(book: Book) {
        title = book.title
        image_url = book.image_url
        author = book.author
        binding.title.text = title
        binding.author.text = author
        Picasso.get().load(image_url).resize(100,300).into(binding.image);
    }
}