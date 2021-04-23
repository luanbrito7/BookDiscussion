package com.example.bookdiscussion

import androidx.lifecycle.LiveData
import androidx.room.*

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
}
