package com.example.bookdiscussion.view.holder
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdiscussion.databinding.CommentrowBinding
import com.example.bookdiscussion.models.Comment

class CommentViewHolder(private val binding: CommentrowBinding) :
    RecyclerView.ViewHolder(binding.root)
{
    var bookId : String = ""
    var body : String = ""

    fun bindTo(comment: Comment) {
        bookId = comment.bookId
        body = comment.body

        binding.body.text = body
    }
}