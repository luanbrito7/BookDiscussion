package com.example.bookdiscussion.dal.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.bookdiscussion.models.Book

@Dao
interface BookDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Delete
    suspend fun destroy(book: Book)

    @Update
    suspend fun update(book: Book)

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookById(id: String) : Book

    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAll() : LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE liked = 1")
    fun getLikedBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE wantToRead = 1")
    fun getToReadBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE reading = 1")
    fun getReadingBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE read = 1")
    fun getReadBooks() : LiveData<List<Book>>
}
