package com.example.bookdiscussion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdiscussion.databinding.CommentrowBinding
import com.example.bookdiscussion.models.Comment
import com.example.bookdiscussion.view.holder.CommentViewHolder

class CommentAdapter (
    private val comments: MutableList<Comment>,
    private val inflater: LayoutInflater) :
    RecyclerView.Adapter<CommentViewHolder>()
{
    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = CommentrowBinding.inflate(inflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindTo(comments.get(position))
    }
}