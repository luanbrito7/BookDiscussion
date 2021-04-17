package com.example.bookdiscussion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : BookRepository = BookRepository(
        BookDB.getInstance(application).bookDAO()
    )

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
}