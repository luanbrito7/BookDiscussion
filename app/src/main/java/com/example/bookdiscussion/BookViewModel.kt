package com.example.bookdiscussion

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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

    suspend fun request(query: String) : MutableList<Book> {
        val gson = Gson()
        val books : MutableList<Book> = mutableListOf()
        var book: Book
        return withContext(Dispatchers.IO) {
            val json = booksApi.get(query)
            var res: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
            var list: List<Any> = res.get("items") as List<Any>
            list.forEach { item ->
                var gM : Map<String, Any> = item as Map<String, Any>
                var volumeInfo : Map<String, Any> = gM.get("volumeInfo") as Map<String, Any>
                var imageLinks = volumeInfo.get("imageLinks")
                var image_url : String = ""
                if (imageLinks != null) {
                    var images = imageLinks as Map<String, Any>
                    Log.d("API", images.toString())
                    image_url = images.get("thumbnail") as String
                }
                var id = gM.get("id")
                if (id == null) return@forEach
                var title = volumeInfo.get("title")
                if (title == null) return@forEach
                var description = volumeInfo.get("description")
                if (description == null) return@forEach
                var authors : ArrayList<String>? = volumeInfo.get("authors") as? ArrayList<String>
                var author = authors?.get(0)
                if (author == null) return@forEach
                book = Book(id as String, title as String, description as String, image_url, author, false, false, false, 1, false)
                books.add(book)
            }
            books
        }
    }

    fun getBookById(id: String) : MutableLiveData<Book> {
        val result = MutableLiveData<Book>()
        viewModelScope.launch(Dispatchers.IO) {
            val book = repository.getBookById(id)
            result.postValue(book)
        }
        return result
    }
}
