package com.example.bookdiscussion.dal.api

import android.content.Context
import com.example.bookdiscussion.R
import java.net.URL

class BookApi (
    private val c: Context
) {
    private val google_api_key = c.getString(R.string.book_api)
    private val base_url = "https://www.googleapis.com/books/v1/volumes"

    suspend fun get(query: String) : String {
        if (query.length > 0) {
            val url = base_url + "?q=" + query + "&?key=" + google_api_key
            val conteudo = URL(url).readText()
            return conteudo
        } else {
            return "error"
        }
    }
}