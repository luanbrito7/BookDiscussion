package com.example.bookdiscussion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdiscussion.models.Book
import com.example.bookdiscussion.view.holder.BookViewHolder
import com.example.bookdiscussion.databinding.BookrowBinding

class BookListAdapter (
    private val books: MutableList<Book>,
    private val inflater: LayoutInflater) :
    RecyclerView.Adapter<BookViewHolder>()
{

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookrowBinding.inflate(inflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindTo(books.get(position))
    }
}