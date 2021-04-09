package com.example.bookdiscussion

data class Book (
        val title:String, val description:String,
        val image_url:String, val author:String, val id:String
    ){

    fun equals(other: Book?): Boolean {
        return id == other?.id
    }
}