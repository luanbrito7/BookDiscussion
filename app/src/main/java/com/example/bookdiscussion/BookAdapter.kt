package com.example.bookdiscussion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdiscussion.databinding.BookrowBinding

class BookAdapter (
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