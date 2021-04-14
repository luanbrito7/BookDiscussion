package com.example.bookdiscussion

import androidx.annotation.WorkerThread

class BookRepository(private val dao: BookDAO) {
    @WorkerThread
    suspend fun getAll() {
        dao.getAll()
    }

    @WorkerThread
    suspend fun insert(book: Book) {
        dao.insert(book)
    }

    @WorkerThread
    suspend fun destroy(book: Book) {
        dao.destroy(book)
    }
}