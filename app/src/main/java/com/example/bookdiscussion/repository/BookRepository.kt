package com.example.bookdiscussion.repository

import androidx.annotation.WorkerThread
import com.example.bookdiscussion.models.Book
import com.example.bookdiscussion.dal.dao.BookDAO

class BookRepository(private val dao: BookDAO) {
    val books = dao.getAll()

    @WorkerThread
    suspend fun insert(book: Book) {
        dao.insert(book)
    }

    @WorkerThread
    suspend fun update(book: Book) {
        dao.update(book)
    }

    @WorkerThread
    suspend fun destroy(book: Book) {
        dao.destroy(book)
    }

    @WorkerThread
    suspend fun getBookById(id: String) : Book {
        return dao.getBookById(id)
    }
}