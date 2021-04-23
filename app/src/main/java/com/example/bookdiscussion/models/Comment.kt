package com.example.bookdiscussion.models

data class Comment (
    val bookId: String,
    val body: String,
    val author: String
) {}