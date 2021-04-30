package com.example.bookdiscussion.view.holder

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdiscussion.models.Book
import com.example.bookdiscussion.activities.BookActivity
import com.example.bookdiscussion.databinding.BookrowBinding
import com.squareup.picasso.Picasso

class BookViewHolder(private val binding: BookrowBinding) :
    RecyclerView.ViewHolder(binding.root)
{
    var id : String = ""
    var title : String = ""
    var image_url : String = ""
    var author : String = ""
    var description : String = ""
    var read : Boolean = false
    var reading : Boolean = false
    var wantToRead : Boolean = false
    var rate : Float = 0F


    init {
        binding.root.setOnClickListener {
            val c = binding.title.context
            val bookIntent = Intent(c, BookActivity::class.java)
            bookIntent.putExtra("id", id)
            bookIntent.putExtra("title", title)
            bookIntent.putExtra("image_url", image_url)
            bookIntent.putExtra("author", author)
            bookIntent.putExtra("description", description)
            bookIntent.putExtra("read", read)
            bookIntent.putExtra("reading", reading)
            bookIntent.putExtra("wantToRead", wantToRead)
            bookIntent.putExtra("rate", rate)
            c.startActivity(bookIntent)
        }
    }

    fun bindTo(book: Book) {
        id = book.id
        title = book.title
        image_url = book.image_url
        author = book.author
        description = book.description
        read = book.read
        reading = book.reading
        wantToRead = book.wantToRead
        rate = book.rate.toFloat()

        binding.title.text = title
        binding.author.text = author
        if (image_url != "") {
            if (image_url[4] != 's') {
                image_url = image_url.replace("http", "https")
            }
            Picasso.get().load(image_url).resize(100,200).into(binding.image);
        }
    }
}