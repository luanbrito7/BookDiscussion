package com.example.bookdiscussion

import androidx.room.*

@Dao
interface BookDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Delete
    suspend fun destroy(book: Book)

    @Query("SELECT * FROM books ORDER BY title ASC")
    suspend fun getAll() : List<Book>
}