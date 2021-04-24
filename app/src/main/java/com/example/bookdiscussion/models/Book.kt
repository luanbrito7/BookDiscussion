package com.example.bookdiscussion.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book (
    @PrimaryKey
    val id:String,
    val title:String,
    val description:String,
    val image_url:String,
    val author:String,
    val reading: Boolean,
    val read: Boolean,
    val wantToRead: Boolean,
    var rate: Int,
    var liked: Boolean
){

    override fun toString(): String {
        return  getStringAttr("id", id) +
                getStringAttr("title", title) +
                getStringAttr("desc", description) +
                getStringAttr("image_url", image_url) +
                getStringAttr("rate", rate)

    }


    fun getStringAttr(label: String, value: Any) : String {
        return label + ": " + value.toString() + "\n"
    }
}