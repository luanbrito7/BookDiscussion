package com.example.bookdiscussion.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bookdiscussion.models.Book
import com.example.bookdiscussion.dal.dao.BookDAO

class BookRepository(private val dao: BookDAO) {
    val books = dao.getAll()
    val likedBooks = dao.getLikedBooks()
    val toReadBooks = dao.getToReadBooks()
    val readingBooks = dao.getReadingBooks()
    val readBooks = dao.getReadBooks()

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
