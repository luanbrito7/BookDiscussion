package com.example.bookdiscussion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookdiscussion.booksApi.BookApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : BookRepository = BookRepository(
        BookDB.getInstance(application).bookDAO()
    )
    private val booksApi : BookApi = BookApi()

    val books = repository.books

    fun insert(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(book)
        }
    }

    fun update(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(book)
        }
    }

    fun destroy(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.destroy(book)
        }
    }

    suspend fun request(query: String) : Map<String, Any> {
        val gson = Gson()
        val mapType = object : TypeToken<Map<String, Any>>() {}.type
        return withContext(Dispatchers.IO) {
            val json = booksApi.get(query)
            var res: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
            res
        }
    }
}