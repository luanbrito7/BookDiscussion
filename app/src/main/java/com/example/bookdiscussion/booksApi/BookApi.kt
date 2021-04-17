package com.example.bookdiscussion.booksApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class BookApi (
    //private val c: Context
) {
    private val google_api_key = "AIzaSyBZZYF_25AKm210FIsiXSpvQ3Qu-hov3Ug"
    private val base_url = "https://www.googleapis.com/books/v1/volumes"
    //val secretValue: String = c.getString(R.string.google_api_key)

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